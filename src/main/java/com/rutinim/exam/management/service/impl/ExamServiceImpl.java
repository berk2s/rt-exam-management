package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.repository.ExamRepository;
import com.rutinim.exam.management.service.ExamService;
import com.rutinim.exam.management.web.exception.ExamNotFoundException;
import com.rutinim.exam.management.web.mappers.ExamMapper;
import com.rutinim.exam.management.web.model.ExamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    @Override
    public List<ExamDto> listExams() {
        return examMapper.examToExamDto(examRepository.findAll());
    }

    @Override
    public ExamDto getExam(UUID examId) {
        Optional<Exam> optionalExam = examRepository.findById(examId);

        if(optionalExam.isEmpty())
            throw new ExamNotFoundException("Exam can not found by id");

        return examMapper.examToExamDto(optionalExam.get());
    }

}
