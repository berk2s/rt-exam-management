package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.List;

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

    private Timestamp createdAt;

    private Timestamp lastModifiedDate;

}
