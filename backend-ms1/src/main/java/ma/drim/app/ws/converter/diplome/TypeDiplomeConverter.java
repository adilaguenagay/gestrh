package  ma.drim.app.ws.converter.diplome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.drim.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.drim.app.zynerator.util.StringUtil;
import ma.drim.app.zynerator.converter.AbstractConverter;
import ma.drim.app.zynerator.util.DateUtil;
import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.ws.dto.diplome.TypeDiplomeDto;

@Component
public class TypeDiplomeConverter {



    public TypeDiplome toItem(TypeDiplomeDto dto) {
        if (dto == null) {
            return null;
        } else {
        TypeDiplome item = new TypeDiplome();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public TypeDiplomeDto toDto(TypeDiplome item) {
        if (item == null) {
            return null;
        } else {
            TypeDiplomeDto dto = new TypeDiplomeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<TypeDiplome> toItem(List<TypeDiplomeDto> dtos) {
        List<TypeDiplome> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (TypeDiplomeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<TypeDiplomeDto> toDto(List<TypeDiplome> items) {
        List<TypeDiplomeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (TypeDiplome item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(TypeDiplomeDto dto, TypeDiplome t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<TypeDiplome> copy(List<TypeDiplomeDto> dtos) {
        List<TypeDiplome> result = new ArrayList<>();
        if (dtos != null) {
            for (TypeDiplomeDto dto : dtos) {
                TypeDiplome instance = new TypeDiplome();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
