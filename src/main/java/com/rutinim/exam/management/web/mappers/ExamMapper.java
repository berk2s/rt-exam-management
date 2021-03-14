package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.web.model.ExamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface ExamMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java( UUID.fromString(examDto.getExamId()) )")
    })
    Exam examDtoToExam(ExamDto examDto);

    @Mappings({
            @Mapping(target = "examId", expression = "java( exam.getId().toString() )")
    })
    ExamDto examToExamDto(Exam exam);

    @Mappings({
            @Mapping(target = "examId", expression = "java( exam.getId().toString() )")
    })
    List<ExamDto> examToExamDto(List<Exam> exam);



}
