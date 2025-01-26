package  ma.drim.app.ws.facade.admin.affectation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.dao.criteria.core.affectation.AffectationCriteria;
import ma.drim.app.service.facade.admin.affectation.AffectationAdminService;
import ma.drim.app.ws.converter.affectation.AffectationConverter;
import ma.drim.app.ws.dto.affectation.AffectationDto;
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
@RequestMapping("/api/admin/affectation/")
public class AffectationRestAdmin {




    @Operation(summary = "Finds a list of all affectations")
    @GetMapping("")
    public ResponseEntity<List<AffectationDto>> findAll() throws Exception {
        ResponseEntity<List<AffectationDto>> res = null;
        List<Affectation> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
            converter.initObject(true);
        List<AffectationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a affectation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<AffectationDto> findById(@PathVariable Long id) {
        Affectation t = service.findById(id);
        if (t != null) {
            converter.init(true);
            AffectationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  affectation")
    @PostMapping("")
    public ResponseEntity<AffectationDto> save(@RequestBody AffectationDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            Affectation myT = converter.toItem(dto);
            Affectation t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                AffectationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  affectation")
    @PutMapping("")
    public ResponseEntity<AffectationDto> update(@RequestBody AffectationDto dto) throws Exception {
        ResponseEntity<AffectationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Affectation t = service.findById(dto.getId());
            converter.copy(dto,t);
            Affectation updated = service.update(t);
            AffectationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of affectation")
    @PostMapping("multiple")
    public ResponseEntity<List<AffectationDto>> delete(@RequestBody List<AffectationDto> dtos) throws Exception {
        ResponseEntity<List<AffectationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<Affectation> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified affectation")
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


    @Operation(summary = "Finds a affectation and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<AffectationDto> findWithAssociatedLists(@PathVariable Long id) {
        Affectation loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        AffectationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds affectations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<AffectationDto>> findByCriteria(@RequestBody AffectationCriteria criteria) throws Exception {
        ResponseEntity<List<AffectationDto>> res = null;
        List<Affectation> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initObject(true);
        List<AffectationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated affectations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody AffectationCriteria criteria) throws Exception {
        List<Affectation> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initObject(true);
        List<AffectationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets affectation data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody AffectationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<AffectationDto> findDtos(List<Affectation> list){
        converter.initObject(true);
        List<AffectationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<AffectationDto> getDtoResponseEntity(AffectationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public AffectationRestAdmin(AffectationAdminService service, AffectationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final AffectationAdminService service;
    private final AffectationConverter converter;





}
