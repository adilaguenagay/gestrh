package  ma.drim.app.ws.dto.employee;

import ma.drim.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


import ma.drim.app.ws.dto.diplome.TypeDiplomeDto;
import ma.drim.app.ws.dto.config.SituationFamilialeDto;
import ma.drim.app.ws.dto.diplome.DiplomeDto;
import ma.drim.app.ws.dto.config.FonctionDto;
import ma.drim.app.ws.dto.config.LocalDto;
import ma.drim.app.ws.dto.config.GenreDto;
import ma.drim.app.ws.dto.config.GradeDto;
import ma.drim.app.ws.dto.config.UniteStructurelleDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto  extends AuditBaseDto {

    private String ppr  ;
    private String nom  ;
    private String prenom  ;
    private String lieuNaissance  ;
    private String photoUrl  ;
    private String telephone  ;
    private String address  ;
    private String email  ;

    private LocalDto local ;
    private SituationFamilialeDto situationFamiliale ;
    private GenreDto genre ;
    private GradeDto grade ;
    private FonctionDto fonction ;
    private UniteStructurelleDto uniteStructurelle ;

    private List<DiplomeDto> diplome ;


    public EmployeeDto(){
        super();
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


    public LocalDto getLocal(){
        return this.local;
    }

    public void setLocal(LocalDto local){
        this.local = local;
    }
    public SituationFamilialeDto getSituationFamiliale(){
        return this.situationFamiliale;
    }

    public void setSituationFamiliale(SituationFamilialeDto situationFamiliale){
        this.situationFamiliale = situationFamiliale;
    }
    public GenreDto getGenre(){
        return this.genre;
    }

    public void setGenre(GenreDto genre){
        this.genre = genre;
    }
    public GradeDto getGrade(){
        return this.grade;
    }

    public void setGrade(GradeDto grade){
        this.grade = grade;
    }
    public FonctionDto getFonction(){
        return this.fonction;
    }

    public void setFonction(FonctionDto fonction){
        this.fonction = fonction;
    }
    public UniteStructurelleDto getUniteStructurelle(){
        return this.uniteStructurelle;
    }

    public void setUniteStructurelle(UniteStructurelleDto uniteStructurelle){
        this.uniteStructurelle = uniteStructurelle;
    }



    public List<DiplomeDto> getDiplome(){
        return this.diplome;
    }

    public void setDiplome(List<DiplomeDto> diplome){
        this.diplome = diplome;
    }



}
