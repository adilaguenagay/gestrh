package ma.drim.app.bean.core.config;

import java.util.List;





import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.bean.core.diplome.Diplome;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "unite_structurelle")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="unite_structurelle_seq",sequenceName="unite_structurelle_seq",allocationSize=1, initialValue = 1)
public class UniteStructurelle  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private UniteStructurelle uniteMere ;

    private List<Employee> employee ;

    public UniteStructurelle(){
        super();
    }

    public UniteStructurelle(Long id){
        this.id = id;
    }

    public UniteStructurelle(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public UniteStructurelle(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="unite_structurelle_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite_mere")
    public UniteStructurelle getUniteMere(){
        return this.uniteMere;
    }
    public void setUniteMere(UniteStructurelle uniteMere){
        this.uniteMere = uniteMere;
    }
    @OneToMany(mappedBy = "uniteStructurelle")
    public List<Employee> getEmployee(){
        return this.employee;
    }

    public void setEmployee(List<Employee> employee){
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniteStructurelle uniteStructurelle = (UniteStructurelle) o;
        return id != null && id.equals(uniteStructurelle.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

