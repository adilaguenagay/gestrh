package ma.drim.app.bean.core.affectation;






import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.bean.core.config.UniteStructurelle;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "affectation")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="affectation_seq",sequenceName="affectation_seq",allocationSize=1, initialValue = 1)
public class Affectation  extends BaseEntity     {




    private Employee employee ;
    private UniteStructurelle uniteMere ;


    public Affectation(){
        super();
    }

    public Affectation(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="affectation_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite_mere")
    public UniteStructurelle getUniteMere(){
        return this.uniteMere;
    }
    public void setUniteMere(UniteStructurelle uniteMere){
        this.uniteMere = uniteMere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Affectation affectation = (Affectation) o;
        return id != null && id.equals(affectation.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

