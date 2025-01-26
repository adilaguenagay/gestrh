package  ma.drim.app.ws.converter.affectation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.drim.app.ws.converter.employee.EmployeeConverter;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.ws.converter.config.UniteStructurelleConverter;
import ma.drim.app.bean.core.config.UniteStructurelle;

import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.bean.core.config.UniteStructurelle;


import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.ws.dto.affectation.AffectationDto;

@Component
public class AffectationConverter {

    @Autowired
    private EmployeeConverter employeeConverter ;
    @Autowired
    private UniteStructurelleConverter uniteStructurelleConverter ;
    private boolean employee;
    private boolean uniteMere;

    public  AffectationConverter() {
        initObject(true);
    }

    public Affectation toItem(AffectationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Affectation item = new Affectation();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(dto.getEmployee() != null && dto.getEmployee().getId() != null){
                item.setEmployee(new Employee());
                item.getEmployee().setId(dto.getEmployee().getId());
                item.getEmployee().setPpr(dto.getEmployee().getPpr());
            }

            if(dto.getUniteMere() != null && dto.getUniteMere().getId() != null){
                item.setUniteMere(new UniteStructurelle());
                item.getUniteMere().setId(dto.getUniteMere().getId());
                item.getUniteMere().setCode(dto.getUniteMere().getCode());
            }




        return item;
        }
    }


    public AffectationDto toDto(Affectation item) {
        if (item == null) {
            return null;
        } else {
            AffectationDto dto = new AffectationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(this.employee && item.getEmployee()!=null) {
                dto.setEmployee(employeeConverter.toDto(item.getEmployee())) ;

            }
            if(this.uniteMere && item.getUniteMere()!=null) {
                dto.setUniteMere(uniteStructurelleConverter.toDto(item.getUniteMere())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.employee = value;
        this.uniteMere = value;
    }
	
    public List<Affectation> toItem(List<AffectationDto> dtos) {
        List<Affectation> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (AffectationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<AffectationDto> toDto(List<Affectation> items) {
        List<AffectationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Affectation item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(AffectationDto dto, Affectation t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEmployee() == null  && dto.getEmployee() != null){
            t.setEmployee(new Employee());
        }else if (t.getEmployee() != null  && dto.getEmployee() != null){
            t.setEmployee(null);
            t.setEmployee(new Employee());
        }
        if(t.getUniteMere() == null  && dto.getUniteMere() != null){
            t.setUniteMere(new UniteStructurelle());
        }else if (t.getUniteMere() != null  && dto.getUniteMere() != null){
            t.setUniteMere(null);
            t.setUniteMere(new UniteStructurelle());
        }
        if (dto.getEmployee() != null)
        employeeConverter.copy(dto.getEmployee(), t.getEmployee());
        if (dto.getUniteMere() != null)
        uniteStructurelleConverter.copy(dto.getUniteMere(), t.getUniteMere());
    }

    public List<Affectation> copy(List<AffectationDto> dtos) {
        List<Affectation> result = new ArrayList<>();
        if (dtos != null) {
            for (AffectationDto dto : dtos) {
                Affectation instance = new Affectation();
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
    public UniteStructurelleConverter getUniteStructurelleConverter(){
        return this.uniteStructurelleConverter;
    }
    public void setUniteStructurelleConverter(UniteStructurelleConverter uniteStructurelleConverter ){
        this.uniteStructurelleConverter = uniteStructurelleConverter;
    }
    public boolean  isEmployee(){
        return this.employee;
    }
    public void  setEmployee(boolean employee){
        this.employee = employee;
    }
    public boolean  isUniteMere(){
        return this.uniteMere;
    }
    public void  setUniteMere(boolean uniteMere){
        this.uniteMere = uniteMere;
    }
}
