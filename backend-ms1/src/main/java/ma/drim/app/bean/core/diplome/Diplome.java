package ma.drim.app.bean.core.diplome;






import ma.drim.app.bean.core.employee.Employee;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diplome")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="diplome_seq",sequenceName="diplome_seq",allocationSize=1, initialValue = 1)
public class Diplome  extends BaseEntity     {




    private Integer anneeObtention = 0;

    @Column(length = 500)
    private String specialie;

    @Column(length = 500)
    private String organisme;

    private TypeDiplome type ;
    private Employee employee ;


    public Diplome(){
        super();
    }

    public Diplome(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="diplome_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public Integer getAnneeObtention(){
        return this.anneeObtention;
    }
    public void setAnneeObtention(Integer anneeObtention){
        this.anneeObtention = anneeObtention;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type")
    public TypeDiplome getType(){
        return this.type;
    }
    public void setType(TypeDiplome type){
        this.type = type;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee")
    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diplome diplome = (Diplome) o;
        return id != null && id.equals(diplome.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

