package ma.drim.app.bean.core.employee;

import java.util.List;





import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.bean.core.config.UniteStructurelle;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.drim.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="employee_seq",sequenceName="employee_seq",allocationSize=1, initialValue = 1)
public class Employee  extends BaseEntity     {




    @Column(length = 500)
    private String ppr;

    @Column(length = 500)
    private String nom;

    @Column(length = 500)
    private String prenom;

    @Column(length = 500)
    private String lieuNaissance;

    @Column(length = 500)
    private String photoUrl;

    @Column(length = 500)
    private String telephone;

    @Column(length = 500)
    private String address;

    @Column(length = 500)
    private String email;

    private Local local ;
    private SituationFamiliale situationFamiliale ;
    private Genre genre ;
    private Grade grade ;
    private Fonction fonction ;
    private UniteStructurelle uniteStructurelle ;

    private List<Diplome> diplome ;

    public Employee(){
        super();
    }

    public Employee(Long id){
        this.id = id;
    }

    public Employee(Long id,String ppr){
        this.id = id;
        this.ppr = ppr ;
    }
    public Employee(String ppr){
        this.ppr = ppr ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="employee_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getPpr(){
        return this.ppr;
    }
    public void setPpr(String ppr){
        this.ppr = ppr;
    }
    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getLieuNaissance(){
        return this.lieuNaissance;
    }
    public void setLieuNaissance(String lieuNaissance){
        this.lieuNaissance = lieuNaissance;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local")
    public Local getLocal(){
        return this.local;
    }
    public void setLocal(Local local){
        this.local = local;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "situation_familiale")
    public SituationFamiliale getSituationFamiliale(){
        return this.situationFamiliale;
    }
    public void setSituationFamiliale(SituationFamiliale situationFamiliale){
        this.situationFamiliale = situationFamiliale;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre")
    public Genre getGenre(){
        return this.genre;
    }
    public void setGenre(Genre genre){
        this.genre = genre;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade")
    public Grade getGrade(){
        return this.grade;
    }
    public void setGrade(Grade grade){
        this.grade = grade;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fonction")
    public Fonction getFonction(){
        return this.fonction;
    }
    public void setFonction(Fonction fonction){
        this.fonction = fonction;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unite_structurelle")
    public UniteStructurelle getUniteStructurelle(){
        return this.uniteStructurelle;
    }
    public void setUniteStructurelle(UniteStructurelle uniteStructurelle){
        this.uniteStructurelle = uniteStructurelle;
    }
    @OneToMany(mappedBy = "employee")
    public List<Diplome> getDiplome(){
        return this.diplome;
    }

    public void setDiplome(List<Diplome> diplome){
        this.diplome = diplome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id != null && id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

