package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.Lesson;
import com.rutinim.exam.management.web.model.LessonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface LessonMapper {

    @Mapping(target = "id", expression = "java( UUID.fromString(lessonDto.getLessonId()) )")
    Lesson lessonDtoToLesson(LessonDto lessonDto);

    LessonDto lessonToLessonDto(Lesson lesson);

    List<LessonDto> lessonToLessonDto(List<Lesson> lessons);

}
