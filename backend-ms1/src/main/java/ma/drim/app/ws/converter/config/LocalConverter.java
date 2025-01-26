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
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.ws.dto.config.LocalDto;

@Component
public class LocalConverter {



    public Local toItem(LocalDto dto) {
        if (dto == null) {
            return null;
        } else {
        Local item = new Local();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public LocalDto toDto(Local item) {
        if (item == null) {
            return null;
        } else {
            LocalDto dto = new LocalDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Local> toItem(List<LocalDto> dtos) {
        List<Local> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (LocalDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<LocalDto> toDto(List<Local> items) {
        List<LocalDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Local item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(LocalDto dto, Local t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Local> copy(List<LocalDto> dtos) {
        List<Local> result = new ArrayList<>();
        if (dtos != null) {
            for (LocalDto dto : dtos) {
                Local instance = new Local();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
