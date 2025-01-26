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

import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.dao.criteria.core.config.FonctionCriteria;
import ma.drim.app.service.facade.admin.config.FonctionAdminService;
import ma.drim.app.ws.converter.config.FonctionConverter;
import ma.drim.app.ws.dto.config.FonctionDto;
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
@RequestMapping("/api/admin/fonction/")
public class FonctionRestAdmin {




    @Operation(summary = "Finds a list of all fonctions")
    @GetMapping("")
    public ResponseEntity<List<FonctionDto>> findAll() throws Exception {
        ResponseEntity<List<FonctionDto>> res = null;
        List<Fonction> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FonctionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all fonctions")
    @GetMapping("optimized")
    public ResponseEntity<List<FonctionDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<FonctionDto>> res = null;
        List<Fonction> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FonctionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a fonction by id")
    @GetMapping("id/{id}")
    public ResponseEntity<FonctionDto> findById(@PathVariable Long id) {
        Fonction t = service.findById(id);
        if (t != null) {
            FonctionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a fonction by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<FonctionDto> findByLibelle(@PathVariable String libelle) {
	    Fonction t = service.findByReferenceEntity(new Fonction(libelle));
        if (t != null) {
            FonctionDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  fonction")
    @PostMapping("")
    public ResponseEntity<FonctionDto> save(@RequestBody FonctionDto dto) throws Exception {
        if(dto!=null){
            Fonction myT = converter.toItem(dto);
            Fonction t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                FonctionDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  fonction")
    @PutMapping("")
    public ResponseEntity<FonctionDto> update(@RequestBody FonctionDto dto) throws Exception {
        ResponseEntity<FonctionDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Fonction t = service.findById(dto.getId());
            converter.copy(dto,t);
            Fonction updated = service.update(t);
            FonctionDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of fonction")
    @PostMapping("multiple")
    public ResponseEntity<List<FonctionDto>> delete(@RequestBody List<FonctionDto> dtos) throws Exception {
        ResponseEntity<List<FonctionDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Fonction> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified fonction")
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


    @Operation(summary = "Finds a fonction and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<FonctionDto> findWithAssociatedLists(@PathVariable Long id) {
        Fonction loaded =  service.findWithAssociatedLists(id);
        FonctionDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds fonctions by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<FonctionDto>> findByCriteria(@RequestBody FonctionCriteria criteria) throws Exception {
        ResponseEntity<List<FonctionDto>> res = null;
        List<Fonction> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<FonctionDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated fonctions by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FonctionCriteria criteria) throws Exception {
        List<Fonction> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<FonctionDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets fonction data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody FonctionCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<FonctionDto> findDtos(List<Fonction> list){
        List<FonctionDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<FonctionDto> getDtoResponseEntity(FonctionDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public FonctionRestAdmin(FonctionAdminService service, FonctionConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final FonctionAdminService service;
    private final FonctionConverter converter;





}
