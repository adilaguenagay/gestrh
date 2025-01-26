package ma.drim.app.bean.core.diplome;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_diplome")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="type_diplome_seq",sequenceName="type_diplome_seq",allocationSize=1, initialValue = 1)
public class TypeDiplome  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public TypeDiplome(){
        super();
    }

    public TypeDiplome(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="type_diplome_seq")
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
        TypeDiplome typeDiplome = (TypeDiplome) o;
        return id != null && id.equals(typeDiplome.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

