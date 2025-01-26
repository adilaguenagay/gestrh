package  ma.drim.app.ws.dto.config;

import ma.drim.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.drim.app.ws.dto.employee.EmployeeDto;
import ma.drim.app.ws.dto.diplome.DiplomeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniteStructurelleDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;

    private UniteStructurelleDto uniteMere ;

    private List<EmployeeDto> employee ;


    public UniteStructurelleDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }


    public UniteStructurelleDto getUniteMere(){
        return this.uniteMere;
    }

    public void setUniteMere(UniteStructurelleDto uniteMere){
        this.uniteMere = uniteMere;
    }



    public List<EmployeeDto> getEmployee(){
        return this.employee;
    }

    public void setEmployee(List<EmployeeDto> employee){
        this.employee = employee;
    }



}
