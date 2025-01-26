package  ma.drim.app.ws.converter.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.drim.app.zynerator.util.ListUtil;

import ma.drim.app.ws.converter.diplome.TypeDiplomeConverter;
import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.ws.converter.config.SituationFamilialeConverter;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.ws.converter.diplome.DiplomeConverter;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.ws.converter.config.FonctionConverter;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.ws.converter.config.LocalConverter;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.ws.converter.config.GenreConverter;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.ws.converter.config.GradeConverter;
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.ws.converter.config.UniteStructurelleConverter;
import ma.drim.app.bean.core.config.UniteStructurelle;

import ma.drim.app.bean.core.config.UniteStructurelle;


import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.ws.dto.employee.EmployeeDto;

@Component
public class EmployeeConverter {

    @Autowired
    private TypeDiplomeConverter typeDiplomeConverter ;
    @Autowired
    private SituationFamilialeConverter situationFamilialeConverter ;
    @Autowired
    private DiplomeConverter diplomeConverter ;
    @Autowired
    private FonctionConverter fonctionConverter ;
    @Autowired
    private LocalConverter localConverter ;
    @Autowired
    private GenreConverter genreConverter ;
    @Autowired
    private GradeConverter gradeConverter ;
    @Autowired
    private UniteStructurelleConverter uniteStructurelleConverter ;
    private boolean local;
    private boolean situationFamiliale;
    private boolean genre;
    private boolean grade;
    private boolean fonction;
    private boolean uniteStructurelle;
    private boolean diplome;

    public  EmployeeConverter() {
        init(true);
    }

    public Employee toItem(EmployeeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Employee item = new Employee();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getPpr()))
                item.setPpr(dto.getPpr());
            if(StringUtil.isNotEmpty(dto.getNom()))
                item.setNom(dto.getNom());
            if(StringUtil.isNotEmpty(dto.getPrenom()))
                item.setPrenom(dto.getPrenom());
            if(StringUtil.isNotEmpty(dto.getLieuNaissance()))
                item.setLieuNaissance(dto.getLieuNaissance());
            if(StringUtil.isNotEmpty(dto.getPhotoUrl()))
                item.setPhotoUrl(dto.getPhotoUrl());
            if(StringUtil.isNotEmpty(dto.getTelephone()))
                item.setTelephone(dto.getTelephone());
            if(StringUtil.isNotEmpty(dto.getAddress()))
                item.setAddress(dto.getAddress());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(this.local && dto.getLocal()!=null)
                item.setLocal(localConverter.toItem(dto.getLocal())) ;

            if(this.situationFamiliale && dto.getSituationFamiliale()!=null)
                item.setSituationFamiliale(situationFamilialeConverter.toItem(dto.getSituationFamiliale())) ;

            if(this.genre && dto.getGenre()!=null)
                item.setGenre(genreConverter.toItem(dto.getGenre())) ;

            if(this.grade && dto.getGrade()!=null)
                item.setGrade(gradeConverter.toItem(dto.getGrade())) ;

            if(this.fonction && dto.getFonction()!=null)
                item.setFonction(fonctionConverter.toItem(dto.getFonction())) ;

            if(dto.getUniteStructurelle() != null && dto.getUniteStructurelle().getId() != null){
                item.setUniteStructurelle(new UniteStructurelle());
                item.getUniteStructurelle().setId(dto.getUniteStructurelle().getId());
                item.getUniteStructurelle().setCode(dto.getUniteStructurelle().getCode());
            }


            if(this.diplome && ListUtil.isNotEmpty(dto.getDiplome()))
                item.setDiplome(diplomeConverter.toItem(dto.getDiplome()));


        return item;
        }
    }


    public EmployeeDto toDto(Employee item) {
        if (item == null) {
            return null;
        } else {
            EmployeeDto dto = new EmployeeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getPpr()))
                dto.setPpr(item.getPpr());
            if(StringUtil.isNotEmpty(item.getNom()))
                dto.setNom(item.getNom());
            if(StringUtil.isNotEmpty(item.getPrenom()))
                dto.setPrenom(item.getPrenom());
            if(StringUtil.isNotEmpty(item.getLieuNaissance()))
                dto.setLieuNaissance(item.getLieuNaissance());
            if(StringUtil.isNotEmpty(item.getPhotoUrl()))
                dto.setPhotoUrl(item.getPhotoUrl());
            if(StringUtil.isNotEmpty(item.getTelephone()))
                dto.setTelephone(item.getTelephone());
            if(StringUtil.isNotEmpty(item.getAddress()))
                dto.setAddress(item.getAddress());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(this.local && item.getLocal()!=null) {
                dto.setLocal(localConverter.toDto(item.getLocal())) ;

            }
            if(this.situationFamiliale && item.getSituationFamiliale()!=null) {
                dto.setSituationFamiliale(situationFamilialeConverter.toDto(item.getSituationFamiliale())) ;

            }
            if(this.genre && item.getGenre()!=null) {
                dto.setGenre(genreConverter.toDto(item.getGenre())) ;

            }
            if(this.grade && item.getGrade()!=null) {
                dto.setGrade(gradeConverter.toDto(item.getGrade())) ;

            }
            if(this.fonction && item.getFonction()!=null) {
                dto.setFonction(fonctionConverter.toDto(item.getFonction())) ;

            }
            if(this.uniteStructurelle && item.getUniteStructurelle()!=null) {
                dto.setUniteStructurelle(uniteStructurelleConverter.toDto(item.getUniteStructurelle())) ;

            }
        if(this.diplome && ListUtil.isNotEmpty(item.getDiplome())){
            diplomeConverter.init(true);
            diplomeConverter.setEmployee(false);
            dto.setDiplome(diplomeConverter.toDto(item.getDiplome()));
            diplomeConverter.setEmployee(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.diplome = value;
    }
    public void initObject(boolean value) {
        this.local = value;
        this.situationFamiliale = value;
        this.genre = value;
        this.grade = value;
        this.fonction = value;
        this.uniteStructurelle = value;
    }
	
    public List<Employee> toItem(List<EmployeeDto> dtos) {
        List<Employee> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EmployeeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EmployeeDto> toDto(List<Employee> items) {
        List<EmployeeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Employee item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EmployeeDto dto, Employee t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getLocal() == null  && dto.getLocal() != null){
            t.setLocal(new Local());
        }else if (t.getLocal() != null  && dto.getLocal() != null){
            t.setLocal(null);
            t.setLocal(new Local());
        }
        if(t.getSituationFamiliale() == null  && dto.getSituationFamiliale() != null){
            t.setSituationFamiliale(new SituationFamiliale());
        }else if (t.getSituationFamiliale() != null  && dto.getSituationFamiliale() != null){
            t.setSituationFamiliale(null);
            t.setSituationFamiliale(new SituationFamiliale());
        }
        if(t.getGenre() == null  && dto.getGenre() != null){
            t.setGenre(new Genre());
        }else if (t.getGenre() != null  && dto.getGenre() != null){
            t.setGenre(null);
            t.setGenre(new Genre());
        }
        if(t.getGrade() == null  && dto.getGrade() != null){
            t.setGrade(new Grade());
        }else if (t.getGrade() != null  && dto.getGrade() != null){
            t.setGrade(null);
            t.setGrade(new Grade());
        }
        if(t.getFonction() == null  && dto.getFonction() != null){
            t.setFonction(new Fonction());
        }else if (t.getFonction() != null  && dto.getFonction() != null){
            t.setFonction(null);
            t.setFonction(new Fonction());
        }
        if(t.getUniteStructurelle() == null  && dto.getUniteStructurelle() != null){
            t.setUniteStructurelle(new UniteStructurelle());
        }else if (t.getUniteStructurelle() != null  && dto.getUniteStructurelle() != null){
            t.setUniteStructurelle(null);
            t.setUniteStructurelle(new UniteStructurelle());
        }
        if (dto.getLocal() != null)
        localConverter.copy(dto.getLocal(), t.getLocal());
        if (dto.getSituationFamiliale() != null)
        situationFamilialeConverter.copy(dto.getSituationFamiliale(), t.getSituationFamiliale());
        if (dto.getGenre() != null)
        genreConverter.copy(dto.getGenre(), t.getGenre());
        if (dto.getGrade() != null)
        gradeConverter.copy(dto.getGrade(), t.getGrade());
        if (dto.getFonction() != null)
        fonctionConverter.copy(dto.getFonction(), t.getFonction());
        if (dto.getUniteStructurelle() != null)
        uniteStructurelleConverter.copy(dto.getUniteStructurelle(), t.getUniteStructurelle());
        if (dto.getDiplome() != null)
            t.setDiplome(diplomeConverter.copy(dto.getDiplome()));
    }

    public List<Employee> copy(List<EmployeeDto> dtos) {
        List<Employee> result = new ArrayList<>();
        if (dtos != null) {
            for (EmployeeDto dto : dtos) {
                Employee instance = new Employee();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public TypeDiplomeConverter getTypeDiplomeConverter(){
        return this.typeDiplomeConverter;
    }
    public void setTypeDiplomeConverter(TypeDiplomeConverter typeDiplomeConverter ){
        this.typeDiplomeConverter = typeDiplomeConverter;
    }
    public SituationFamilialeConverter getSituationFamilialeConverter(){
        return this.situationFamilialeConverter;
    }
    public void setSituationFamilialeConverter(SituationFamilialeConverter situationFamilialeConverter ){
        this.situationFamilialeConverter = situationFamilialeConverter;
    }
    public DiplomeConverter getDiplomeConverter(){
        return this.diplomeConverter;
    }
    public void setDiplomeConverter(DiplomeConverter diplomeConverter ){
        this.diplomeConverter = diplomeConverter;
    }
    public FonctionConverter getFonctionConverter(){
        return this.fonctionConverter;
    }
    public void setFonctionConverter(FonctionConverter fonctionConverter ){
        this.fonctionConverter = fonctionConverter;
    }
    public LocalConverter getLocalConverter(){
        return this.localConverter;
    }
    public void setLocalConverter(LocalConverter localConverter ){
        this.localConverter = localConverter;
    }
    public GenreConverter getGenreConverter(){
        return this.genreConverter;
    }
    public void setGenreConverter(GenreConverter genreConverter ){
        this.genreConverter = genreConverter;
    }
    public GradeConverter getGradeConverter(){
        return this.gradeConverter;
    }
    public void setGradeConverter(GradeConverter gradeConverter ){
        this.gradeConverter = gradeConverter;
    }
    public UniteStructurelleConverter getUniteStructurelleConverter(){
        return this.uniteStructurelleConverter;
    }
    public void setUniteStructurelleConverter(UniteStructurelleConverter uniteStructurelleConverter ){
        this.uniteStructurelleConverter = uniteStructurelleConverter;
    }
    public boolean  isLocal(){
        return this.local;
    }
    public void  setLocal(boolean local){
        this.local = local;
    }
    public boolean  isSituationFamiliale(){
        return this.situationFamiliale;
    }
    public void  setSituationFamiliale(boolean situationFamiliale){
        this.situationFamiliale = situationFamiliale;
    }
    public boolean  isGenre(){
        return this.genre;
    }
    public void  setGenre(boolean genre){
        this.genre = genre;
    }
    public boolean  isGrade(){
        return this.grade;
    }
    public void  setGrade(boolean grade){
        this.grade = grade;
    }
    public boolean  isFonction(){
        return this.fonction;
    }
    public void  setFonction(boolean fonction){
        this.fonction = fonction;
    }
    public boolean  isUniteStructurelle(){
        return this.uniteStructurelle;
    }
    public void  setUniteStructurelle(boolean uniteStructurelle){
        this.uniteStructurelle = uniteStructurelle;
    }
    public boolean  isDiplome(){
        return this.diplome ;
    }
    public void  setDiplome(boolean diplome ){
        this.diplome  = diplome ;
    }
}
