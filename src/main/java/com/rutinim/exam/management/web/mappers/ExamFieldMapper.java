package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface ExamFieldMapper {

    @Mappings(
            @Mapping(target = "id", expression = "java( UUID.fromString(examFieldDto.getExamFieldId()) )")
    )
    ExamField examFieldDtoToExamField(ExamFieldDto examFieldDto);

    @Mappings(
            @Mapping(target = "examFieldId", expression = "java( examField.getId().toString() )")
    )
    ExamFieldDto examFieldToExamFieldDto(ExamField examField);

}
