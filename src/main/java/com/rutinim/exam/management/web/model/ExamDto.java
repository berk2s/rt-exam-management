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

    private String examId;

    private String examName;

    private ExamTypeDto examTypeDto;

}
