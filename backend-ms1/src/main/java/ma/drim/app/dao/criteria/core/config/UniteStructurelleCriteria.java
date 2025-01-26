package  ma.drim.app.dao.criteria.core.config;



import ma.drim.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class UniteStructurelleCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String libelle;
    private String libelleLike;

    private UniteStructurelleCriteria uniteMere ;
    private List<UniteStructurelleCriteria> uniteMeres ;


    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
    public String getLibelleLike(){
        return this.libelleLike;
    }
    public void setLibelleLike(String libelleLike){
        this.libelleLike = libelleLike;
    }


    public UniteStructurelleCriteria getUniteMere(){
        return this.uniteMere;
    }

    public void setUniteMere(UniteStructurelleCriteria uniteMere){
        this.uniteMere = uniteMere;
    }
    public List<UniteStructurelleCriteria> getUniteMeres(){
        return this.uniteMeres;
    }

    public void setUniteMeres(List<UniteStructurelleCriteria> uniteMeres){
        this.uniteMeres = uniteMeres;
    }
}
