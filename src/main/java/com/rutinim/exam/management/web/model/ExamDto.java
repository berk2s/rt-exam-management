package com.rutinim.exam.management.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

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
    @JsonProperty("examTypes")
    private List<ExamTypeDto> examTypeDtos;



}
