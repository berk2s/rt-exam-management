package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.ExamType;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class}, uses = {ExamFieldMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamTypeMapper {

    @Mappings({
            @Mapping(target = "examFields", source = "examFieldsDto"),
    })
    ExamType examTypeDtoToExamType(ExamTypeDto examTypeDto);

    @Mappings({
            @Mapping(target = "examFieldsDto", source = "examFields"),
            @Mapping(target = "examTypeId", expression = "java( examType.getId().toString() )")
    })
    ExamTypeDto examTypeToExamTypeDto(ExamType examType);

    @Mappings({
            @Mapping(target = "examFieldsDto", source = "examFields"),
            @Mapping(target = "examTypeId", expression = "java( examType.getId().toString() )")
    })
    List<ExamTypeDto> examTypeToExamTypeDto(List<ExamType> examType);

}
