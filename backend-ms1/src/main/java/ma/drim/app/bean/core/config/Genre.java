package ma.drim.app.bean.core.config;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genre")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="genre_seq",sequenceName="genre_seq",allocationSize=1, initialValue = 1)
public class Genre  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;



    public Genre(){
        super();
    }

    public Genre(Long id){
        this.id = id;
    }

    public Genre(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public Genre(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="genre_seq")
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
        Genre genre = (Genre) o;
        return id != null && id.equals(genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

