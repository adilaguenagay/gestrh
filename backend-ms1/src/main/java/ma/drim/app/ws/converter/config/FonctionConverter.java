package  ma.drim.app.ws.converter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.ws.dto.config.FonctionDto;

@Component
public class FonctionConverter {



    public Fonction toItem(FonctionDto dto) {
        if (dto == null) {
            return null;
        } else {
        Fonction item = new Fonction();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public FonctionDto toDto(Fonction item) {
        if (item == null) {
            return null;
        } else {
            FonctionDto dto = new FonctionDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Fonction> toItem(List<FonctionDto> dtos) {
        List<Fonction> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (FonctionDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<FonctionDto> toDto(List<Fonction> items) {
        List<FonctionDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Fonction item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(FonctionDto dto, Fonction t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Fonction> copy(List<FonctionDto> dtos) {
        List<Fonction> result = new ArrayList<>();
        if (dtos != null) {
            for (FonctionDto dto : dtos) {
                Fonction instance = new Fonction();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
