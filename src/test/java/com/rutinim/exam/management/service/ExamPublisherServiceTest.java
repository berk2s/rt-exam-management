package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.repository.ExamPublisherRepository;
import com.rutinim.exam.management.service.impl.ExamPublisherServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamPublisherMapper;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamPublisherServiceTest {

    @Mock
    private ExamPublisherRepository examPublisherRepository;

    @Spy
    private final ExamPublisherMapper examPublisherMapper = Mappers.getMapper(ExamPublisherMapper.class);

    @InjectMocks
    private ExamPublisherServiceImpl examPublisherService;

    ExamPublisher examPublisher;
    ExamPublisherDto examPublisherDto;
    UUID publisherId;
    UUID examPublisherId;

    @BeforeEach
    void setUp() {
        publisherId = UUID.randomUUID();
        examPublisherId = UUID.randomUUID();
        examPublisher = ExamPublisher.builder()
                .id(examPublisherId)
                .publisherId(publisherId)
                .publisherName("A simple publisher")
                .build();

        examPublisherDto = examPublisherMapper.examPublisherToExamPublisherDto(examPublisher);
    }

    @DisplayName("Should Find By Id Exam Publisher Successfully")
    @Test
    void shouldFindByIdExamPublisherSuccessfully() {
        when(examPublisherRepository.findById(examPublisherId)).thenReturn(Optional.ofNullable(examPublisher));

        ExamPublisherDto returnedExamPublisherDto = examPublisherService.getExamPublisher(examPublisherId);

        assertThat(returnedExamPublisherDto)
                .isEqualTo(examPublisherDto)
                .isNotNull();

        verify(examPublisherRepository).findById(examPublisherId);
    }

    @DisplayName("Should List Exam Publisher Successfully")
    @Test
    void shouldListExamPublisherSuccessfully() {
        when(examPublisherRepository.findAll()).thenReturn(Collections.singletonList(examPublisher));

        List<ExamPublisherDto> returnedExamPublisherDtos = examPublisherService.listExamPublishers();

        assertThat(returnedExamPublisherDtos.get(0))
                .isEqualTo(examPublisherDto)
                .isNotNull();

        verify(examPublisherRepository).findAll();
    }
}