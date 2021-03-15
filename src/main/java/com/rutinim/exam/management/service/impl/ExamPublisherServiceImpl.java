package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.domain.PublisherSeries;
import com.rutinim.exam.management.repository.ExamPublisherRepository;
import com.rutinim.exam.management.service.ExamPublisherService;
import com.rutinim.exam.management.web.exception.ExamPublisherNotFoundException;
import com.rutinim.exam.management.web.mappers.ExamPublisherMapper;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
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
            throw new ExamPublisherNotFoundException("Exam publisher doesn't exits");

        return examPublisherMapper.examPublisherToExamPublisherDto(optionalExamPublisher.get());
    }

    @Override
    public void saveExamPublisher(ExamPublisherDto examPublisherDto) {
        examPublisherRepository.save(examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto));
    }

    @Override
    public void updateExamPublisher(ExamPublisherDto examPublisherDto) {
        ExamPublisher tempExamPublisher = examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto);

        ExamPublisher examPublisher = examPublisherRepository.getOne(tempExamPublisher.getId());

        examPublisher.setPublisherName(tempExamPublisher.getPublisherName());
        examPublisher.setPublisherId(tempExamPublisher.getPublisherId());
        examPublisher.setExamImage(tempExamPublisher.getExamImage());
        examPublisher.setNumberOfSeries(tempExamPublisher.getNumberOfSeries());
        examPublisher.setExam(tempExamPublisher.getExam());

        examPublisherRepository.save(examPublisher);
    }

    @Override
    public void addPublisherSeries(ExamPublisherDto examPublisherDto) {
        ExamPublisher tempExamPublisher = examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto);

        ExamPublisher examPublisher = examPublisherRepository.getOne(tempExamPublisher.getId());

        tempExamPublisher.getPublisherSeries().forEach(examPublisher::addPublisherSeries);

        examPublisherRepository.save(examPublisher);
    }

    @Override
    public void deleteExamPublisher(UUID examPublisherId) {
        ExamPublisher examPublisher = examPublisherRepository.getOne(examPublisherId);
        examPublisherRepository.delete(examPublisher);
    }

    @Override
    public void deletePublisherSeries(ExamPublisherDto examPublisherDto) {
        ExamPublisher tempExamPublisher = examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto);

        ExamPublisher examPublisher = examPublisherRepository.getOne(tempExamPublisher.getId());

        examPublisher.getPublisherSeries().removeIf(p -> tempExamPublisher.getPublisherSeries().stream().anyMatch(f -> f.getId().equals(p.getId())));

        examPublisherRepository.save(examPublisher);
    }
}
