package  ma.drim.app.ws.dto.diplome;

import ma.drim.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.drim.app.ws.dto.employee.EmployeeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiplomeDto  extends AuditBaseDto {

    private Integer anneeObtention  = 0 ;
    private String specialie  ;
    private String organisme  ;

    private TypeDiplomeDto type ;
    private EmployeeDto employee ;



    public DiplomeDto(){
        super();
    }




    public Integer getAnneeObtention(){
        return this.anneeObtention;
    }
    public void setAnneeObtention(Integer anneeObtention){
        this.anneeObtention = anneeObtention;
    }


    public String getSpecialie(){
        return this.specialie;
    }
    public void setSpecialie(String specialie){
        this.specialie = specialie;
    }


    public String getOrganisme(){
        return this.organisme;
    }
    public void setOrganisme(String organisme){
        this.organisme = organisme;
    }


    public TypeDiplomeDto getType(){
        return this.type;
    }

    public void setType(TypeDiplomeDto type){
        this.type = type;
    }
    public EmployeeDto getEmployee(){
        return this.employee;
    }

    public void setEmployee(EmployeeDto employee){
        this.employee = employee;
    }






}
