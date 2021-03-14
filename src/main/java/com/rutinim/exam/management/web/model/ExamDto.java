package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDto {

    @Null
    private String examId;

    @NotNull
    private String examName;

    @Null
    private Timestamp createdAt;

    @Null
    private Timestamp lastModifiedDate;

    @NotNull
    private ExamTypeDto examType;


}
