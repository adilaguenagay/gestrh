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

import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;
import ma.drim.app.service.facade.admin.config.UniteStructurelleAdminService;
import ma.drim.app.ws.converter.config.UniteStructurelleConverter;
import ma.drim.app.ws.dto.config.UniteStructurelleDto;
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
@RequestMapping("/api/admin/uniteStructurelle/")
public class UniteStructurelleRestAdmin {




    @Operation(summary = "Finds a list of all uniteStructurelles")
    @GetMapping("")
    public ResponseEntity<List<UniteStructurelleDto>> findAll() throws Exception {
        ResponseEntity<List<UniteStructurelleDto>> res = null;
        List<UniteStructurelle> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<UniteStructurelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all uniteStructurelles")
    @GetMapping("optimized")
    public ResponseEntity<List<UniteStructurelleDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<UniteStructurelleDto>> res = null;
        List<UniteStructurelle> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<UniteStructurelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a uniteStructurelle by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UniteStructurelleDto> findById(@PathVariable Long id) {
        UniteStructurelle t = service.findById(id);
        if (t != null) {
            converter.init(true);
            UniteStructurelleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a uniteStructurelle by code")
    @GetMapping("code/{code}")
    public ResponseEntity<UniteStructurelleDto> findByCode(@PathVariable String code) {
	    UniteStructurelle t = service.findByReferenceEntity(new UniteStructurelle(code));
        if (t != null) {
            converter.init(true);
            UniteStructurelleDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  uniteStructurelle")
    @PostMapping("")
    public ResponseEntity<UniteStructurelleDto> save(@RequestBody UniteStructurelleDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            UniteStructurelle myT = converter.toItem(dto);
            UniteStructurelle t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                UniteStructurelleDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  uniteStructurelle")
    @PutMapping("")
    public ResponseEntity<UniteStructurelleDto> update(@RequestBody UniteStructurelleDto dto) throws Exception {
        ResponseEntity<UniteStructurelleDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            UniteStructurelle t = service.findById(dto.getId());
            converter.copy(dto,t);
            UniteStructurelle updated = service.update(t);
            UniteStructurelleDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of uniteStructurelle")
    @PostMapping("multiple")
    public ResponseEntity<List<UniteStructurelleDto>> delete(@RequestBody List<UniteStructurelleDto> dtos) throws Exception {
        ResponseEntity<List<UniteStructurelleDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<UniteStructurelle> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified uniteStructurelle")
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

    @Operation(summary = "find by uniteMere id")
    @GetMapping("uniteMere/id/{id}")
    public List<UniteStructurelleDto> findByUniteMereId(@PathVariable Long id){
        return findDtos(service.findByUniteMereId(id));
    }
    @Operation(summary = "delete by uniteMere id")
    @DeleteMapping("uniteMere/id/{id}")
    public int deleteByUniteMereId(@PathVariable Long id){
        return service.deleteByUniteMereId(id);
    }

    @Operation(summary = "Finds a uniteStructurelle and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<UniteStructurelleDto> findWithAssociatedLists(@PathVariable Long id) {
        UniteStructurelle loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        UniteStructurelleDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds uniteStructurelles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<UniteStructurelleDto>> findByCriteria(@RequestBody UniteStructurelleCriteria criteria) throws Exception {
        ResponseEntity<List<UniteStructurelleDto>> res = null;
        List<UniteStructurelle> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<UniteStructurelleDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated uniteStructurelles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody UniteStructurelleCriteria criteria) throws Exception {
        List<UniteStructurelle> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<UniteStructurelleDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets uniteStructurelle data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody UniteStructurelleCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<UniteStructurelleDto> findDtos(List<UniteStructurelle> list){
        converter.initList(false);
        converter.initObject(true);
        List<UniteStructurelleDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<UniteStructurelleDto> getDtoResponseEntity(UniteStructurelleDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public UniteStructurelleRestAdmin(UniteStructurelleAdminService service, UniteStructurelleConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final UniteStructurelleAdminService service;
    private final UniteStructurelleConverter converter;





}
