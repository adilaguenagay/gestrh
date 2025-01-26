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
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.ws.dto.config.GradeDto;

@Component
public class GradeConverter {



    public Grade toItem(GradeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Grade item = new Grade();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());



        return item;
        }
    }


    public GradeDto toDto(Grade item) {
        if (item == null) {
            return null;
        } else {
            GradeDto dto = new GradeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());


        return dto;
        }
    }


	
    public List<Grade> toItem(List<GradeDto> dtos) {
        List<Grade> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (GradeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<GradeDto> toDto(List<Grade> items) {
        List<GradeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Grade item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(GradeDto dto, Grade t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Grade> copy(List<GradeDto> dtos) {
        List<Grade> result = new ArrayList<>();
        if (dtos != null) {
            for (GradeDto dto : dtos) {
                Grade instance = new Grade();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
