package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamFieldDto;

import java.util.UUID;

public interface ExamFieldService {

    ExamFieldDto getExamField(UUID examFieldId);

    void updateExamField(UUID examFieldId, ExamFieldDto examFieldDto);

}
