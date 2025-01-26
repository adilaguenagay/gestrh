package  ma.drim.app.ws.dto.affectation;

import ma.drim.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.drim.app.ws.dto.employee.EmployeeDto;
import ma.drim.app.ws.dto.config.UniteStructurelleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AffectationDto  extends AuditBaseDto {


    private EmployeeDto employee ;
    private UniteStructurelleDto uniteMere ;



    public AffectationDto(){
        super();
    }




    public EmployeeDto getEmployee(){
        return this.employee;
    }

    public void setEmployee(EmployeeDto employee){
        this.employee = employee;
    }
    public UniteStructurelleDto getUniteMere(){
        return this.uniteMere;
    }

    public void setUniteMere(UniteStructurelleDto uniteMere){
        this.uniteMere = uniteMere;
    }






}
