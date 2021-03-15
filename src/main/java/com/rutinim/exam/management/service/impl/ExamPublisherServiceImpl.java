package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.repository.ExamPublisherRepository;
import com.rutinim.exam.management.service.ExamPublisherService;
import com.rutinim.exam.management.web.exception.ExamPublisherNotFoundException;
import com.rutinim.exam.management.web.mappers.ExamPublisherMapper;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamPublisherServiceImpl implements ExamPublisherService {

    private final ExamPublisherRepository examPublisherRepository;
    private final ExamPublisherMapper examPublisherMapper;

    @Override
    public List<ExamPublisherDto> listExamPublishers() {
        return examPublisherMapper.examPublisherToExamPublisherDto(examPublisherRepository.findAll());
    }

    @Override
    public ExamPublisherDto getExamPublisher(UUID examPublisherId) {
        Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisherId);

        if(optionalExamPublisher.isEmpty())
            throw new ExamPublisherNotFoundException("Exam Publisher can not found by Id");

        return examPublisherMapper.examPublisherToExamPublisherDto(optionalExamPublisher.get());
    }
}
