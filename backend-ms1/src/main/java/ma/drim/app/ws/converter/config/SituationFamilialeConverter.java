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
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.ws.dto.config.SituationFamilialeDto;

@Component
public class SituationFamilialeConverter {



    public SituationFamiliale toItem(SituationFamilialeDto dto) {
        if (dto == null) {
            return null;
        } else {
        SituationFamiliale item = new SituationFamiliale();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public SituationFamilialeDto toDto(SituationFamiliale item) {
        if (item == null) {
            return null;
        } else {
            SituationFamilialeDto dto = new SituationFamilialeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<SituationFamiliale> toItem(List<SituationFamilialeDto> dtos) {
        List<SituationFamiliale> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SituationFamilialeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SituationFamilialeDto> toDto(List<SituationFamiliale> items) {
        List<SituationFamilialeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (SituationFamiliale item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SituationFamilialeDto dto, SituationFamiliale t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<SituationFamiliale> copy(List<SituationFamilialeDto> dtos) {
        List<SituationFamiliale> result = new ArrayList<>();
        if (dtos != null) {
            for (SituationFamilialeDto dto : dtos) {
                SituationFamiliale instance = new SituationFamiliale();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
