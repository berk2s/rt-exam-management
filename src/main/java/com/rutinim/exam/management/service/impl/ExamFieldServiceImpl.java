package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.repository.ExamFieldRepository;
import com.rutinim.exam.management.service.ExamFieldService;
import com.rutinim.exam.management.web.exception.ExamTypeNotFoundException;
import com.rutinim.exam.management.web.mappers.ExamFieldMapper;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamFieldServiceImpl implements ExamFieldService {

    private final ExamFieldRepository examFieldRepository;
    private final ExamFieldMapper examFieldMapper;

    @Override
    public ExamFieldDto getExamField(UUID examFieldId) {
        Optional<ExamField> optionalExamField = examFieldRepository.findById(examFieldId);

        if(optionalExamField.isEmpty())
            throw new ExamTypeNotFoundException("Exam Field doesn't exists");

        return examFieldMapper.examFieldToExamFieldDto(optionalExamField.get());
    }

    @Override
    public void updateExamField(UUID examFieldId, ExamFieldDto examFieldDto) {
        ExamField examField = examFieldRepository.getOne(examFieldId);

        examField.setFieldName(examFieldDto.getFieldName());
        examField.setNumberOfQuestions(examFieldDto.getNumberOfQuestions());

        examFieldRepository.save(examField);
    }
}
