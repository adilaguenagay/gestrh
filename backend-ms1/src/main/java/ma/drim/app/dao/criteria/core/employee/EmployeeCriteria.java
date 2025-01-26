package  ma.drim.app.dao.criteria.core.employee;


import ma.drim.app.dao.criteria.core.config.SituationFamilialeCriteria;
import ma.drim.app.dao.criteria.core.config.FonctionCriteria;
import ma.drim.app.dao.criteria.core.config.LocalCriteria;
import ma.drim.app.dao.criteria.core.config.GenreCriteria;
import ma.drim.app.dao.criteria.core.config.GradeCriteria;
import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;

import ma.drim.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class EmployeeCriteria extends  BaseCriteria  {

    private String ppr;
    private String pprLike;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String lieuNaissance;
    private String lieuNaissanceLike;
    private String photoUrl;
    private String photoUrlLike;
    private String telephone;
    private String telephoneLike;
    private String address;
    private String addressLike;
    private String email;
    private String emailLike;

    private LocalCriteria local ;
    private List<LocalCriteria> locals ;
    private SituationFamilialeCriteria situationFamiliale ;
    private List<SituationFamilialeCriteria> situationFamiliales ;
    private GenreCriteria genre ;
    private List<GenreCriteria> genres ;
    private GradeCriteria grade ;
    private List<GradeCriteria> grades ;
    private FonctionCriteria fonction ;
    private List<FonctionCriteria> fonctions ;
    private UniteStructurelleCriteria uniteStructurelle ;
    private List<UniteStructurelleCriteria> uniteStructurelles ;


    public String getPpr(){
        return this.ppr;
    }
    public void setPpr(String ppr){
        this.ppr = ppr;
    }
    public String getPprLike(){
        return this.pprLike;
    }
    public void setPprLike(String pprLike){
        this.pprLike = pprLike;
    }

    public String getNom(){
        return this.nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getNomLike(){
        return this.nomLike;
    }
    public void setNomLike(String nomLike){
        this.nomLike = nomLike;
    }

    public String getPrenom(){
        return this.prenom;
    }
    public void setPrenom(String prenom){
        this.prenom = prenom;
    }
    public String getPrenomLike(){
        return this.prenomLike;
    }
    public void setPrenomLike(String prenomLike){
        this.prenomLike = prenomLike;
    }

    public String getLieuNaissance(){
        return this.lieuNaissance;
    }
    public void setLieuNaissance(String lieuNaissance){
        this.lieuNaissance = lieuNaissance;
    }
    public String getLieuNaissanceLike(){
        return this.lieuNaissanceLike;
    }
    public void setLieuNaissanceLike(String lieuNaissanceLike){
        this.lieuNaissanceLike = lieuNaissanceLike;
    }

    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
    public String getPhotoUrlLike(){
        return this.photoUrlLike;
    }
    public void setPhotoUrlLike(String photoUrlLike){
        this.photoUrlLike = photoUrlLike;
    }

    public String getTelephone(){
        return this.telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephoneLike(){
        return this.telephoneLike;
    }
    public void setTelephoneLike(String telephoneLike){
        this.telephoneLike = telephoneLike;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddressLike(){
        return this.addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }


    public LocalCriteria getLocal(){
        return this.local;
    }

    public void setLocal(LocalCriteria local){
        this.local = local;
    }
    public List<LocalCriteria> getLocals(){
        return this.locals;
    }

    public void setLocals(List<LocalCriteria> locals){
        this.locals = locals;
    }
    public SituationFamilialeCriteria getSituationFamiliale(){
        return this.situationFamiliale;
    }

    public void setSituationFamiliale(SituationFamilialeCriteria situationFamiliale){
        this.situationFamiliale = situationFamiliale;
    }
    public List<SituationFamilialeCriteria> getSituationFamiliales(){
        return this.situationFamiliales;
    }

    public void setSituationFamiliales(List<SituationFamilialeCriteria> situationFamiliales){
        this.situationFamiliales = situationFamiliales;
    }
    public GenreCriteria getGenre(){
        return this.genre;
    }

    public void setGenre(GenreCriteria genre){
        this.genre = genre;
    }
    public List<GenreCriteria> getGenres(){
        return this.genres;
    }

    public void setGenres(List<GenreCriteria> genres){
        this.genres = genres;
    }
    public GradeCriteria getGrade(){
        return this.grade;
    }

    public void setGrade(GradeCriteria grade){
        this.grade = grade;
    }
    public List<GradeCriteria> getGrades(){
        return this.grades;
    }

    public void setGrades(List<GradeCriteria> grades){
        this.grades = grades;
    }
    public FonctionCriteria getFonction(){
        return this.fonction;
    }

    public void setFonction(FonctionCriteria fonction){
        this.fonction = fonction;
    }
    public List<FonctionCriteria> getFonctions(){
        return this.fonctions;
    }

    public void setFonctions(List<FonctionCriteria> fonctions){
        this.fonctions = fonctions;
    }
    public UniteStructurelleCriteria getUniteStructurelle(){
        return this.uniteStructurelle;
    }

    public void setUniteStructurelle(UniteStructurelleCriteria uniteStructurelle){
        this.uniteStructurelle = uniteStructurelle;
    }
    public List<UniteStructurelleCriteria> getUniteStructurelles(){
        return this.uniteStructurelles;
    }

    public void setUniteStructurelles(List<UniteStructurelleCriteria> uniteStructurelles){
        this.uniteStructurelles = uniteStructurelles;
    }
}
