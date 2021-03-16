package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamTypeDto {

    @Null
    private String examTypeId;

    @NotBlank
    private String typeName;

    @NotNull
    private Integer examDuration;

    @NotNull
    private Boolean isOnePiece;

    @NotNull
    private Boolean isPreparatoryExam;

    @NotNull
    private List<ExamFieldDto> examFieldsDto;

    @NotNull
    private Boolean isExamFieldsChanged;

    private ExamDto examDto;

    @NotNull
    private UUID examId;


}
