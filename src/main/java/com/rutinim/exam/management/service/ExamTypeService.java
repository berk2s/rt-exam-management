package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamTypeDto;

import java.util.List;
import java.util.UUID;

public interface ExamTypeService {

    List<ExamTypeDto> listExamTypes();

    ExamTypeDto getExamType(UUID examTypeId);

    void saveExamType(ExamTypeDto examTypeDto);

    void addExamField(UUID examTypeId, ExamTypeDto examTypeDto);

    void updateExamType(UUID examTypeId, ExamTypeDto examTypeDto);

    void deleteExamType(UUID examTypeId);

}
