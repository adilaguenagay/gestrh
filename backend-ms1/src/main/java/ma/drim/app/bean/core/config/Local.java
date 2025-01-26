package ma.drim.app.bean.core.config;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "local")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="local_seq",sequenceName="local_seq",allocationSize=1, initialValue = 1)
public class Local  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public Local(){
        super();
    }

    public Local(Long id){
        this.id = id;
    }

    public Local(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Local(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="local_seq")
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
        Local local = (Local) o;
        return id != null && id.equals(local.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

