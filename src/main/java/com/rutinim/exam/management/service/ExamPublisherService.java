package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamPublisherDto;

import java.util.List;
import java.util.UUID;

public interface ExamPublisherService {

    List<ExamPublisherDto> listExamPublishers();

    ExamPublisherDto getExamPublisher(UUID examPublisherId);

}
