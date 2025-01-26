package  ma.drim.app.ws.converter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.drim.app.zynerator.util.ListUtil;

import ma.drim.app.ws.converter.employee.EmployeeConverter;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.ws.converter.config.SituationFamilialeConverter;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.ws.converter.diplome.DiplomeConverter;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.ws.converter.config.FonctionConverter;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.ws.converter.config.LocalConverter;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.ws.converter.config.GenreConverter;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.ws.converter.config.GradeConverter;
import ma.drim.app.bean.core.config.Grade;



import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.ws.dto.config.UniteStructurelleDto;

@Component
public class UniteStructurelleConverter {

    @Autowired
    private EmployeeConverter employeeConverter ;
    @Autowired
    private SituationFamilialeConverter situationFamilialeConverter ;
    @Autowired
    private DiplomeConverter diplomeConverter ;
    @Autowired
    private FonctionConverter fonctionConverter ;
    @Autowired
    private LocalConverter localConverter ;
    @Autowired
    private GenreConverter genreConverter ;
    @Autowired
    private GradeConverter gradeConverter ;
    private boolean uniteMere;
    private boolean employee;

    public  UniteStructurelleConverter() {
        init(true);
    }

    public UniteStructurelle toItem(UniteStructurelleDto dto) {
        if (dto == null) {
            return null;
        } else {
        UniteStructurelle item = new UniteStructurelle();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(this.uniteMere && dto.getUniteMere()!=null)
                item.setUniteMere(toItem(dto.getUniteMere())) ;


            if(this.employee && ListUtil.isNotEmpty(dto.getEmployee()))
                item.setEmployee(employeeConverter.toItem(dto.getEmployee()));


        return item;
        }
    }


    public UniteStructurelleDto toDto(UniteStructurelle item) {
        if (item == null) {
            return null;
        } else {
            UniteStructurelleDto dto = new UniteStructurelleDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(this.uniteMere && item.getUniteMere()!=null) {
                this.setUniteMere(false);
                dto.setUniteMere(toDto(item.getUniteMere())) ;
                this.setUniteMere(true);

            }
        if(this.employee && ListUtil.isNotEmpty(item.getEmployee())){
            employeeConverter.init(true);
            employeeConverter.setUniteStructurelle(false);
            dto.setEmployee(employeeConverter.toDto(item.getEmployee()));
            employeeConverter.setUniteStructurelle(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.employee = value;
    }
    public void initObject(boolean value) {
        this.uniteMere = value;
    }
	
    public List<UniteStructurelle> toItem(List<UniteStructurelleDto> dtos) {
        List<UniteStructurelle> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (UniteStructurelleDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<UniteStructurelleDto> toDto(List<UniteStructurelle> items) {
        List<UniteStructurelleDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (UniteStructurelle item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(UniteStructurelleDto dto, UniteStructurelle t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getUniteMere() == null  && dto.getUniteMere() != null){
            t.setUniteMere(new UniteStructurelle());
        }else if (t.getUniteMere() != null  && dto.getUniteMere() != null){
            t.setUniteMere(null);
            t.setUniteMere(new UniteStructurelle());
        }
        if (dto.getUniteMere() != null)
            copy(dto.getUniteMere(), t.getUniteMere());
        if (dto.getEmployee() != null)
            t.setEmployee(employeeConverter.copy(dto.getEmployee()));
    }

    public List<UniteStructurelle> copy(List<UniteStructurelleDto> dtos) {
        List<UniteStructurelle> result = new ArrayList<>();
        if (dtos != null) {
            for (UniteStructurelleDto dto : dtos) {
                UniteStructurelle instance = new UniteStructurelle();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public EmployeeConverter getEmployeeConverter(){
        return this.employeeConverter;
    }
    public void setEmployeeConverter(EmployeeConverter employeeConverter ){
        this.employeeConverter = employeeConverter;
    }
    public SituationFamilialeConverter getSituationFamilialeConverter(){
        return this.situationFamilialeConverter;
    }
    public void setSituationFamilialeConverter(SituationFamilialeConverter situationFamilialeConverter ){
        this.situationFamilialeConverter = situationFamilialeConverter;
    }
    public DiplomeConverter getDiplomeConverter(){
        return this.diplomeConverter;
    }
    public void setDiplomeConverter(DiplomeConverter diplomeConverter ){
        this.diplomeConverter = diplomeConverter;
    }
    public FonctionConverter getFonctionConverter(){
        return this.fonctionConverter;
    }
    public void setFonctionConverter(FonctionConverter fonctionConverter ){
        this.fonctionConverter = fonctionConverter;
    }
    public LocalConverter getLocalConverter(){
        return this.localConverter;
    }
    public void setLocalConverter(LocalConverter localConverter ){
        this.localConverter = localConverter;
    }
    public GenreConverter getGenreConverter(){
        return this.genreConverter;
    }
    public void setGenreConverter(GenreConverter genreConverter ){
        this.genreConverter = genreConverter;
    }
    public GradeConverter getGradeConverter(){
        return this.gradeConverter;
    }
    public void setGradeConverter(GradeConverter gradeConverter ){
        this.gradeConverter = gradeConverter;
    }
    public boolean  isUniteMere(){
        return this.uniteMere;
    }
    public void  setUniteMere(boolean uniteMere){
        this.uniteMere = uniteMere;
    }
    public boolean  isEmployee(){
        return this.employee ;
    }
    public void  setEmployee(boolean employee ){
        this.employee  = employee ;
    }
}
