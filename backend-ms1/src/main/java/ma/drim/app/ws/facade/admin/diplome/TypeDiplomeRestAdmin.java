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

import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.dao.criteria.core.diplome.TypeDiplomeCriteria;
import ma.drim.app.service.facade.admin.diplome.TypeDiplomeAdminService;
import ma.drim.app.ws.converter.diplome.TypeDiplomeConverter;
import ma.drim.app.ws.dto.diplome.TypeDiplomeDto;
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
@RequestMapping("/api/admin/typeDiplome/")
public class TypeDiplomeRestAdmin {




    @Operation(summary = "Finds a list of all typeDiplomes")
    @GetMapping("")
    public ResponseEntity<List<TypeDiplomeDto>> findAll() throws Exception {
        ResponseEntity<List<TypeDiplomeDto>> res = null;
        List<TypeDiplome> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDiplomeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a typeDiplome by id")
    @GetMapping("id/{id}")
    public ResponseEntity<TypeDiplomeDto> findById(@PathVariable Long id) {
        TypeDiplome t = service.findById(id);
        if (t != null) {
            TypeDiplomeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  typeDiplome")
    @PostMapping("")
    public ResponseEntity<TypeDiplomeDto> save(@RequestBody TypeDiplomeDto dto) throws Exception {
        if(dto!=null){
            TypeDiplome myT = converter.toItem(dto);
            TypeDiplome t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                TypeDiplomeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  typeDiplome")
    @PutMapping("")
    public ResponseEntity<TypeDiplomeDto> update(@RequestBody TypeDiplomeDto dto) throws Exception {
        ResponseEntity<TypeDiplomeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            TypeDiplome t = service.findById(dto.getId());
            converter.copy(dto,t);
            TypeDiplome updated = service.update(t);
            TypeDiplomeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of typeDiplome")
    @PostMapping("multiple")
    public ResponseEntity<List<TypeDiplomeDto>> delete(@RequestBody List<TypeDiplomeDto> dtos) throws Exception {
        ResponseEntity<List<TypeDiplomeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<TypeDiplome> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified typeDiplome")
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


    @Operation(summary = "Finds a typeDiplome and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<TypeDiplomeDto> findWithAssociatedLists(@PathVariable Long id) {
        TypeDiplome loaded =  service.findWithAssociatedLists(id);
        TypeDiplomeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds typeDiplomes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<TypeDiplomeDto>> findByCriteria(@RequestBody TypeDiplomeCriteria criteria) throws Exception {
        ResponseEntity<List<TypeDiplomeDto>> res = null;
        List<TypeDiplome> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<TypeDiplomeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated typeDiplomes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody TypeDiplomeCriteria criteria) throws Exception {
        List<TypeDiplome> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<TypeDiplomeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets typeDiplome data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody TypeDiplomeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<TypeDiplomeDto> findDtos(List<TypeDiplome> list){
        List<TypeDiplomeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<TypeDiplomeDto> getDtoResponseEntity(TypeDiplomeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public TypeDiplomeRestAdmin(TypeDiplomeAdminService service, TypeDiplomeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final TypeDiplomeAdminService service;
    private final TypeDiplomeConverter converter;





}
