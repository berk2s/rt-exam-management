package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.Null;

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

}
