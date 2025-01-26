package ma.drim.app.bean.core.config;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "situation_familiale")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="situation_familiale_seq",sequenceName="situation_familiale_seq",allocationSize=1, initialValue = 1)
public class SituationFamiliale  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public SituationFamiliale(){
        super();
    }

    public SituationFamiliale(Long id){
        this.id = id;
    }

    public SituationFamiliale(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public SituationFamiliale(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="situation_familiale_seq")
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
        SituationFamiliale situationFamiliale = (SituationFamiliale) o;
        return id != null && id.equals(situationFamiliale.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

