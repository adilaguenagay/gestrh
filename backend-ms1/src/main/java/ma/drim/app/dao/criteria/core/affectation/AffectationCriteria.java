package  ma.drim.app.dao.criteria.core.affectation;


import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;
import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;

import ma.drim.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class AffectationCriteria extends  BaseCriteria  {


    private EmployeeCriteria employee ;
    private List<EmployeeCriteria> employees ;
    private UniteStructurelleCriteria uniteMere ;
    private List<UniteStructurelleCriteria> uniteMeres ;



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
    public UniteStructurelleCriteria getUniteMere(){
        return this.uniteMere;
    }

    public void setUniteMere(UniteStructurelleCriteria uniteMere){
        this.uniteMere = uniteMere;
    }
    public List<UniteStructurelleCriteria> getUniteMeres(){
        return this.uniteMeres;
    }

    public void setUniteMeres(List<UniteStructurelleCriteria> uniteMeres){
        this.uniteMeres = uniteMeres;
    }
}
