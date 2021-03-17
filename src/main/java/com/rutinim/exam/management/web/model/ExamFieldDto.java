package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamFieldDto {

    @Null
    private String examFieldId;

    private String fieldName;

    private Integer numberOfQuestions;

    private LessonDto lesson;

    private ExamTypeDto examType;

    private UUID lessonId;

    private Boolean isChanged;

}
