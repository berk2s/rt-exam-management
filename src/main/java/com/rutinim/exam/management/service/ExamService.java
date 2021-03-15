package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamDto;

import java.util.List;
import java.util.UUID;

public interface ExamService {

    List<ExamDto> listExams();

    ExamDto getExam(UUID examId);

    void saveExam(ExamDto examDto);

    void updateExam(ExamDto examDto);

    void deleteExam(UUID examId);

}
