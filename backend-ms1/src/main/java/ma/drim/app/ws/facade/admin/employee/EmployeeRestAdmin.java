package  ma.drim.app.ws.facade.admin.employee;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;
import ma.drim.app.service.facade.admin.employee.EmployeeAdminService;
import ma.drim.app.ws.converter.employee.EmployeeConverter;
import ma.drim.app.ws.dto.employee.EmployeeDto;
import ma.drim.app.zynerator.controller.AbstractController;
import ma.drim.app.zynerator.dto.AuditEntityDto;
import ma.drim.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.drim.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.drim.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/employee/")
public class EmployeeRestAdmin {




    @Operation(summary = "Finds a list of all employees")
    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> findAll() throws Exception {
        ResponseEntity<List<EmployeeDto>> res = null;
        List<Employee> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<EmployeeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all employees")
    @GetMapping("optimized")
    public ResponseEntity<List<EmployeeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<EmployeeDto>> res = null;
        List<Employee> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EmployeeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a employee by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable Long id) {
        Employee t = service.findById(id);
        if (t != null) {
            converter.init(true);
            EmployeeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a employee by ppr")
    @GetMapping("ppr/{ppr}")
    public ResponseEntity<EmployeeDto> findByPpr(@PathVariable String ppr) {
	    Employee t = service.findByReferenceEntity(new Employee(ppr));
        if (t != null) {
            converter.init(true);
            EmployeeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  employee")
    @PostMapping("")
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Employee myT = converter.toItem(dto);
            Employee t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EmployeeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  employee")
    @PutMapping("")
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto dto) throws Exception {
        ResponseEntity<EmployeeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Employee t = service.findById(dto.getId());
            converter.copy(dto,t);
            Employee updated = service.update(t);
            EmployeeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of employee")
    @PostMapping("multiple")
    public ResponseEntity<List<EmployeeDto>> delete(@RequestBody List<EmployeeDto> dtos) throws Exception {
        ResponseEntity<List<EmployeeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Employee> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified employee")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }

    @Operation(summary = "find by local id")
    @GetMapping("local/id/{id}")
    public List<EmployeeDto> findByLocalId(@PathVariable Long id){
        return findDtos(service.findByLocalId(id));
    }
    @Operation(summary = "delete by local id")
    @DeleteMapping("local/id/{id}")
    public int deleteByLocalId(@PathVariable Long id){
        return service.deleteByLocalId(id);
    }
    @Operation(summary = "find by situationFamiliale id")
    @GetMapping("situationFamiliale/id/{id}")
    public List<EmployeeDto> findBySituationFamilialeId(@PathVariable Long id){
        return findDtos(service.findBySituationFamilialeId(id));
    }
    @Operation(summary = "delete by situationFamiliale id")
    @DeleteMapping("situationFamiliale/id/{id}")
    public int deleteBySituationFamilialeId(@PathVariable Long id){
        return service.deleteBySituationFamilialeId(id);
    }
    @Operation(summary = "find by genre id")
    @GetMapping("genre/id/{id}")
    public List<EmployeeDto> findByGenreId(@PathVariable Long id){
        return findDtos(service.findByGenreId(id));
    }
    @Operation(summary = "delete by genre id")
    @DeleteMapping("genre/id/{id}")
    public int deleteByGenreId(@PathVariable Long id){
        return service.deleteByGenreId(id);
    }
    @Operation(summary = "find by grade id")
    @GetMapping("grade/id/{id}")
    public List<EmployeeDto> findByGradeId(@PathVariable Long id){
        return findDtos(service.findByGradeId(id));
    }
    @Operation(summary = "delete by grade id")
    @DeleteMapping("grade/id/{id}")
    public int deleteByGradeId(@PathVariable Long id){
        return service.deleteByGradeId(id);
    }
    @Operation(summary = "find by fonction id")
    @GetMapping("fonction/id/{id}")
    public List<EmployeeDto> findByFonctionId(@PathVariable Long id){
        return findDtos(service.findByFonctionId(id));
    }
    @Operation(summary = "delete by fonction id")
    @DeleteMapping("fonction/id/{id}")
    public int deleteByFonctionId(@PathVariable Long id){
        return service.deleteByFonctionId(id);
    }

    @Operation(summary = "Finds a employee and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EmployeeDto> findWithAssociatedLists(@PathVariable Long id) {
        Employee loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        EmployeeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds employees by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EmployeeDto>> findByCriteria(@RequestBody EmployeeCriteria criteria) throws Exception {
        ResponseEntity<List<EmployeeDto>> res = null;
        List<Employee> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<EmployeeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated employees by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EmployeeCriteria criteria) throws Exception {
        List<Employee> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<EmployeeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets employee data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EmployeeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EmployeeDto> findDtos(List<Employee> list){
        converter.initList(false);
        converter.initObject(true);
        List<EmployeeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EmployeeDto> getDtoResponseEntity(EmployeeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EmployeeRestAdmin(EmployeeAdminService service, EmployeeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EmployeeAdminService service;
    private final EmployeeConverter converter;





}
