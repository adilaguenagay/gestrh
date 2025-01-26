package  ma.drim.app.dao.criteria.core.diplome;


import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;

import ma.drim.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class DiplomeCriteria extends  BaseCriteria  {

    private String anneeObtention;
    private String anneeObtentionMin;
    private String anneeObtentionMax;
    private String specialie;
    private String specialieLike;
    private String organisme;
    private String organismeLike;

    private TypeDiplomeCriteria type ;
    private List<TypeDiplomeCriteria> types ;
    private EmployeeCriteria employee ;
    private List<EmployeeCriteria> employees ;


    public String getAnneeObtention(){
        return this.anneeObtention;
    }
    public void setAnneeObtention(String anneeObtention){
        this.anneeObtention = anneeObtention;
    }   
    public String getAnneeObtentionMin(){
        return this.anneeObtentionMin;
    }
    public void setAnneeObtentionMin(String anneeObtentionMin){
        this.anneeObtentionMin = anneeObtentionMin;
    }
    public String getAnneeObtentionMax(){
        return this.anneeObtentionMax;
    }
    public void setAnneeObtentionMax(String anneeObtentionMax){
        this.anneeObtentionMax = anneeObtentionMax;
    }
      
    public String getSpecialie(){
        return this.specialie;
    }
    public void setSpecialie(String specialie){
        this.specialie = specialie;
    }
    public String getSpecialieLike(){
        return this.specialieLike;
    }
    public void setSpecialieLike(String specialieLike){
        this.specialieLike = specialieLike;
    }

    public String getOrganisme(){
        return this.organisme;
    }
    public void setOrganisme(String organisme){
        this.organisme = organisme;
    }
    public String getOrganismeLike(){
        return this.organismeLike;
    }
    public void setOrganismeLike(String organismeLike){
        this.organismeLike = organismeLike;
    }


    public TypeDiplomeCriteria getType(){
        return this.type;
    }

    public void setType(TypeDiplomeCriteria type){
        this.type = type;
    }
    public List<TypeDiplomeCriteria> getTypes(){
        return this.types;
    }

    public void setTypes(List<TypeDiplomeCriteria> types){
        this.types = types;
    }
    public EmployeeCriteria getEmployee(){
        return this.employee;
    }

    public void setEmployee(EmployeeCriteria employee){
        this.employee = employee;
    }
    public List<EmployeeCriteria> getEmployees(){
        return this.employees;
    }

    public void setEmployees(List<EmployeeCriteria> employees){
        this.employees = employees;
    }
}
