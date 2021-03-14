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

    private String typeName;

    private Integer examDuration;

    private Boolean isOnePiece;

    private Boolean isPreparatoryExam;

    private List<ExamFieldDto> examFieldsDto;

    @Null
    private Timestamp createdAt;

    @Null
    private Timestamp lastModifiedDate;

}
