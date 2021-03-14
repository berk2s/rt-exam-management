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
public class ExamTypeDto {

    @Null
    private String examTypeId;

    @NotNull
    private String typeName;

    @NotNull
    private Integer examDuration;

    @NotNull
    private Boolean isOnePiece;

    @NotNull
    private Boolean isPreparatoryExam;

    @NotNull
    private List<ExamFieldDto> examFields;

    @Null
    private Timestamp createdAt;

    @Null
    private Timestamp lastModifiedDate;

}
