package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDto {

    @Null
    private String examId;

    @NotBlank
    private String examName;

    @NotNull
    private ExamTypeDto examTypeDto;

}
