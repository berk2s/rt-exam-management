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
    public void updateExamPublisher(UUID examPublisherId, ExamPublisherDto examPublisherDto) {
        ExamPublisher tempExamPublisher = examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto);

        ExamPublisher examPublisher = examPublisherRepository.getOne(examPublisherId);

        examPublisher.setPublisherName(tempExamPublisher.getPublisherName());
        examPublisher.setPublisherId(tempExamPublisher.getPublisherId());
        examPublisher.setExamImage(tempExamPublisher.getExamImage());
        examPublisher.setNumberOfSeries(tempExamPublisher.getNumberOfSeries());
        examPublisher.setExam(tempExamPublisher.getExam());

        if(tempExamPublisher.getPublisherSeries().size() > 0) {
            examPublisher.getPublisherSeries().removeIf(p -> examPublisherDto.getPublisherSeriesDto().stream().noneMatch(f -> f.getPublisherSeriesId().equals(p.getId().toString())));
        }else{
            examPublisher.getPublisherSeries().clear();
        }

        examPublisherRepository.save(examPublisher);
    }

    @Override
    public void addPublisherSeries(UUID examPublisherId, ExamPublisherDto examPublisherDto) {
        ExamPublisher tempExamPublisher = examPublisherMapper.examPublisherDtoToExamPublisher(examPublisherDto);

        ExamPublisher examPublisher = examPublisherRepository.getOne(examPublisherId);

        tempExamPublisher.getPublisherSeries().forEach(examPublisher::addPublisherSeries);

        examPublisherRepository.save(examPublisher);
    }

    @Override
    public void deleteExamPublisher(UUID examPublisherId) {
        ExamPublisher examPublisher = examPublisherRepository.getOne(examPublisherId);
        examPublisherRepository.delete(examPublisher);
    }

}
