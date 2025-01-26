package ma.drim.app.bean.core.config;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fonction")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="fonction_seq",sequenceName="fonction_seq",allocationSize=1, initialValue = 1)
public class Fonction  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public Fonction(){
        super();
    }

    public Fonction(Long id){
        this.id = id;
    }

    public Fonction(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Fonction(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="fonction_seq")
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
        Fonction fonction = (Fonction) o;
        return id != null && id.equals(fonction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

