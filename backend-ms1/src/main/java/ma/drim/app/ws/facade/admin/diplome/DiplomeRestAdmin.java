package  ma.drim.app.ws.facade.admin.diplome;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.dao.criteria.core.diplome.DiplomeCriteria;
import ma.drim.app.service.facade.admin.diplome.DiplomeAdminService;
import ma.drim.app.ws.converter.diplome.DiplomeConverter;
import ma.drim.app.ws.dto.diplome.DiplomeDto;
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
@RequestMapping("/api/admin/diplome/")
public class DiplomeRestAdmin {




    @Operation(summary = "Finds a list of all diplomes")
    @GetMapping("")
    public ResponseEntity<List<DiplomeDto>> findAll() throws Exception {
        ResponseEntity<List<DiplomeDto>> res = null;
        List<Diplome> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<DiplomeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a diplome by id")
    @GetMapping("id/{id}")
    public ResponseEntity<DiplomeDto> findById(@PathVariable Long id) {
        Diplome t = service.findById(id);
        if (t != null) {
            converter.init(true);
            DiplomeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  diplome")
    @PostMapping("")
    public ResponseEntity<DiplomeDto> save(@RequestBody DiplomeDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Diplome myT = converter.toItem(dto);
            Diplome t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                DiplomeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  diplome")
    @PutMapping("")
    public ResponseEntity<DiplomeDto> update(@RequestBody DiplomeDto dto) throws Exception {
        ResponseEntity<DiplomeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Diplome t = service.findById(dto.getId());
            converter.copy(dto,t);
            Diplome updated = service.update(t);
            DiplomeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of diplome")
    @PostMapping("multiple")
    public ResponseEntity<List<DiplomeDto>> delete(@RequestBody List<DiplomeDto> dtos) throws Exception {
        ResponseEntity<List<DiplomeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Diplome> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified diplome")
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

    @Operation(summary = "find by type id")
    @GetMapping("type/id/{id}")
    public List<DiplomeDto> findByTypeId(@PathVariable Long id){
        return findDtos(service.findByTypeId(id));
    }
    @Operation(summary = "delete by type id")
    @DeleteMapping("type/id/{id}")
    public int deleteByTypeId(@PathVariable Long id){
        return service.deleteByTypeId(id);
    }
    @Operation(summary = "find by employee id")
    @GetMapping("employee/id/{id}")
    public List<DiplomeDto> findByEmployeeId(@PathVariable Long id){
        return findDtos(service.findByEmployeeId(id));
    }
    @Operation(summary = "delete by employee id")
    @DeleteMapping("employee/id/{id}")
    public int deleteByEmployeeId(@PathVariable Long id){
        return service.deleteByEmployeeId(id);
    }

    @Operation(summary = "Finds a diplome and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<DiplomeDto> findWithAssociatedLists(@PathVariable Long id) {
        Diplome loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        DiplomeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds diplomes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<DiplomeDto>> findByCriteria(@RequestBody DiplomeCriteria criteria) throws Exception {
        ResponseEntity<List<DiplomeDto>> res = null;
        List<Diplome> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<DiplomeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated diplomes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody DiplomeCriteria criteria) throws Exception {
        List<Diplome> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<DiplomeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets diplome data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody DiplomeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<DiplomeDto> findDtos(List<Diplome> list){
        converter.initObject(true);
        List<DiplomeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<DiplomeDto> getDtoResponseEntity(DiplomeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public DiplomeRestAdmin(DiplomeAdminService service, DiplomeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final DiplomeAdminService service;
    private final DiplomeConverter converter;





}
