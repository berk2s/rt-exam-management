package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.Null;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonDto {

    @Null
    private String lessonId;

    private String lessonName;


}
