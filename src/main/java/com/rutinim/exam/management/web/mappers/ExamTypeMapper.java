package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.ExamType;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface ExamTypeMapper {

    @Mappings(
            @Mapping(target = "id", expression = "java( UUID.fromString(examTypeDto.getExamTypeId()) )")
    )
    ExamType examTypeDtoToExamType(ExamTypeDto examTypeDto);

    @Mappings(
            @Mapping(target = "examTypeId", expression = "java( examType.getId().toString() )")
    )
    ExamTypeDto examTypeToExamTypeDto(ExamType examType);

}
