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

import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.dao.criteria.core.config.SituationFamilialeCriteria;
import ma.drim.app.service.facade.admin.config.SituationFamilialeAdminService;
import ma.drim.app.ws.converter.config.SituationFamilialeConverter;
import ma.drim.app.ws.dto.config.SituationFamilialeDto;
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
@RequestMapping("/api/admin/situationFamiliale/")
public class SituationFamilialeRestAdmin {




    @Operation(summary = "Finds a list of all situationFamiliales")
    @GetMapping("")
    public ResponseEntity<List<SituationFamilialeDto>> findAll() throws Exception {
        ResponseEntity<List<SituationFamilialeDto>> res = null;
        List<SituationFamiliale> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SituationFamilialeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all situationFamiliales")
    @GetMapping("optimized")
    public ResponseEntity<List<SituationFamilialeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<SituationFamilialeDto>> res = null;
        List<SituationFamiliale> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SituationFamilialeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a situationFamiliale by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SituationFamilialeDto> findById(@PathVariable Long id) {
        SituationFamiliale t = service.findById(id);
        if (t != null) {
            SituationFamilialeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a situationFamiliale by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<SituationFamilialeDto> findByLibelle(@PathVariable String libelle) {
	    SituationFamiliale t = service.findByReferenceEntity(new SituationFamiliale(libelle));
        if (t != null) {
            SituationFamilialeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  situationFamiliale")
    @PostMapping("")
    public ResponseEntity<SituationFamilialeDto> save(@RequestBody SituationFamilialeDto dto) throws Exception {
        if(dto!=null){
            SituationFamiliale myT = converter.toItem(dto);
            SituationFamiliale t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SituationFamilialeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  situationFamiliale")
    @PutMapping("")
    public ResponseEntity<SituationFamilialeDto> update(@RequestBody SituationFamilialeDto dto) throws Exception {
        ResponseEntity<SituationFamilialeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            SituationFamiliale t = service.findById(dto.getId());
            converter.copy(dto,t);
            SituationFamiliale updated = service.update(t);
            SituationFamilialeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of situationFamiliale")
    @PostMapping("multiple")
    public ResponseEntity<List<SituationFamilialeDto>> delete(@RequestBody List<SituationFamilialeDto> dtos) throws Exception {
        ResponseEntity<List<SituationFamilialeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<SituationFamiliale> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified situationFamiliale")
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


    @Operation(summary = "Finds a situationFamiliale and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SituationFamilialeDto> findWithAssociatedLists(@PathVariable Long id) {
        SituationFamiliale loaded =  service.findWithAssociatedLists(id);
        SituationFamilialeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds situationFamiliales by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SituationFamilialeDto>> findByCriteria(@RequestBody SituationFamilialeCriteria criteria) throws Exception {
        ResponseEntity<List<SituationFamilialeDto>> res = null;
        List<SituationFamiliale> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<SituationFamilialeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated situationFamiliales by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SituationFamilialeCriteria criteria) throws Exception {
        List<SituationFamiliale> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<SituationFamilialeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets situationFamiliale data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SituationFamilialeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SituationFamilialeDto> findDtos(List<SituationFamiliale> list){
        List<SituationFamilialeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SituationFamilialeDto> getDtoResponseEntity(SituationFamilialeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SituationFamilialeRestAdmin(SituationFamilialeAdminService service, SituationFamilialeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SituationFamilialeAdminService service;
    private final SituationFamilialeConverter converter;





}
