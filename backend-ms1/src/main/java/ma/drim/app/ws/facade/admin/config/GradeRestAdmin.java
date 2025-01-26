package  ma.drim.app.ws.facade.admin.config;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.dao.criteria.core.config.GradeCriteria;
import ma.drim.app.service.facade.admin.config.GradeAdminService;
import ma.drim.app.ws.converter.config.GradeConverter;
import ma.drim.app.ws.dto.config.GradeDto;
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
@RequestMapping("/api/admin/grade/")
public class GradeRestAdmin {




    @Operation(summary = "Finds a list of all grades")
    @GetMapping("")
    public ResponseEntity<List<GradeDto>> findAll() throws Exception {
        ResponseEntity<List<GradeDto>> res = null;
        List<Grade> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GradeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all grades")
    @GetMapping("optimized")
    public ResponseEntity<List<GradeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<GradeDto>> res = null;
        List<Grade> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GradeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a grade by id")
    @GetMapping("id/{id}")
    public ResponseEntity<GradeDto> findById(@PathVariable Long id) {
        Grade t = service.findById(id);
        if (t != null) {
            GradeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a grade by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<GradeDto> findByLibelle(@PathVariable String libelle) {
	    Grade t = service.findByReferenceEntity(new Grade(libelle));
        if (t != null) {
            GradeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  grade")
    @PostMapping("")
    public ResponseEntity<GradeDto> save(@RequestBody GradeDto dto) throws Exception {
        if(dto!=null){
            Grade myT = converter.toItem(dto);
            Grade t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                GradeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  grade")
    @PutMapping("")
    public ResponseEntity<GradeDto> update(@RequestBody GradeDto dto) throws Exception {
        ResponseEntity<GradeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Grade t = service.findById(dto.getId());
            converter.copy(dto,t);
            Grade updated = service.update(t);
            GradeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of grade")
    @PostMapping("multiple")
    public ResponseEntity<List<GradeDto>> delete(@RequestBody List<GradeDto> dtos) throws Exception {
        ResponseEntity<List<GradeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Grade> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified grade")
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


    @Operation(summary = "Finds a grade and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<GradeDto> findWithAssociatedLists(@PathVariable Long id) {
        Grade loaded =  service.findWithAssociatedLists(id);
        GradeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds grades by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<GradeDto>> findByCriteria(@RequestBody GradeCriteria criteria) throws Exception {
        ResponseEntity<List<GradeDto>> res = null;
        List<Grade> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<GradeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated grades by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody GradeCriteria criteria) throws Exception {
        List<Grade> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<GradeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets grade data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody GradeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<GradeDto> findDtos(List<Grade> list){
        List<GradeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<GradeDto> getDtoResponseEntity(GradeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public GradeRestAdmin(GradeAdminService service, GradeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final GradeAdminService service;
    private final GradeConverter converter;





}
