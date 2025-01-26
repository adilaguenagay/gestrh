package ma.drim.app.bean.core.config;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grade")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="grade_seq",sequenceName="grade_seq",allocationSize=1, initialValue = 1)
public class Grade  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public Grade(){
        super();
    }

    public Grade(Long id){
        this.id = id;
    }

    public Grade(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Grade(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="grade_seq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id != null && id.equals(grade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

