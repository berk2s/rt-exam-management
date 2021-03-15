package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamPublisherDto;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;

import java.util.List;
import java.util.UUID;

public interface ExamPublisherService {

    List<ExamPublisherDto> listExamPublishers();

    ExamPublisherDto getExamPublisher(UUID examPublisherId);

    void saveExamPublisher(ExamPublisherDto examPublisherDto);

    void updateExamPublisher(ExamPublisherDto examPublisherDto);

    void addPublisherSeries(ExamPublisherDto examPublisherDto);

    void deleteExamPublisher(UUID examPublisherId);

    void deletePublisherSeries(ExamPublisherDto examPublisherDto);

}
