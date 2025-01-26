package  ma.drim.app.ws.converter.diplome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.drim.app.ws.converter.employee.EmployeeConverter;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.ws.converter.diplome.TypeDiplomeConverter;
import ma.drim.app.bean.core.diplome.TypeDiplome;

import ma.drim.app.bean.core.employee.Employee;


import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.ws.dto.diplome.DiplomeDto;

@Component
public class DiplomeConverter {

    @Autowired
    private EmployeeConverter employeeConverter ;
    @Autowired
    private TypeDiplomeConverter typeDiplomeConverter ;
    private boolean type;
    private boolean employee;

    public  DiplomeConverter() {
        initObject(true);
    }

    public Diplome toItem(DiplomeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Diplome item = new Diplome();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getAnneeObtention()))
                item.setAnneeObtention(dto.getAnneeObtention());
            if(StringUtil.isNotEmpty(dto.getSpecialie()))
                item.setSpecialie(dto.getSpecialie());
            if(StringUtil.isNotEmpty(dto.getOrganisme()))
                item.setOrganisme(dto.getOrganisme());
            if(this.type && dto.getType()!=null)
                item.setType(typeDiplomeConverter.toItem(dto.getType())) ;

            if(dto.getEmployee() != null && dto.getEmployee().getId() != null){
                item.setEmployee(new Employee());
                item.getEmployee().setId(dto.getEmployee().getId());
                item.getEmployee().setPpr(dto.getEmployee().getPpr());
            }




        return item;
        }
    }


    public DiplomeDto toDto(Diplome item) {
        if (item == null) {
            return null;
        } else {
            DiplomeDto dto = new DiplomeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAnneeObtention()))
                dto.setAnneeObtention(item.getAnneeObtention());
            if(StringUtil.isNotEmpty(item.getSpecialie()))
                dto.setSpecialie(item.getSpecialie());
            if(StringUtil.isNotEmpty(item.getOrganisme()))
                dto.setOrganisme(item.getOrganisme());
            if(this.type && item.getType()!=null) {
                dto.setType(typeDiplomeConverter.toDto(item.getType())) ;

            }
            if(this.employee && item.getEmployee()!=null) {
                dto.setEmployee(employeeConverter.toDto(item.getEmployee())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.type = value;
        this.employee = value;
    }
	
    public List<Diplome> toItem(List<DiplomeDto> dtos) {
        List<Diplome> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DiplomeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DiplomeDto> toDto(List<Diplome> items) {
        List<DiplomeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Diplome item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DiplomeDto dto, Diplome t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getType() == null  && dto.getType() != null){
            t.setType(new TypeDiplome());
        }else if (t.getType() != null  && dto.getType() != null){
            t.setType(null);
            t.setType(new TypeDiplome());
        }
        if(t.getEmployee() == null  && dto.getEmployee() != null){
            t.setEmployee(new Employee());
        }else if (t.getEmployee() != null  && dto.getEmployee() != null){
            t.setEmployee(null);
            t.setEmployee(new Employee());
        }
        if (dto.getType() != null)
        typeDiplomeConverter.copy(dto.getType(), t.getType());
        if (dto.getEmployee() != null)
        employeeConverter.copy(dto.getEmployee(), t.getEmployee());
    }

    public List<Diplome> copy(List<DiplomeDto> dtos) {
        List<Diplome> result = new ArrayList<>();
        if (dtos != null) {
            for (DiplomeDto dto : dtos) {
                Diplome instance = new Diplome();
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
    public TypeDiplomeConverter getTypeDiplomeConverter(){
        return this.typeDiplomeConverter;
    }
    public void setTypeDiplomeConverter(TypeDiplomeConverter typeDiplomeConverter ){
        this.typeDiplomeConverter = typeDiplomeConverter;
    }
    public boolean  isType(){
        return this.type;
    }
    public void  setType(boolean type){
        this.type = type;
    }
    public boolean  isEmployee(){
        return this.employee;
    }
    public void  setEmployee(boolean employee){
        this.employee = employee;
    }
}
