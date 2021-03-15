package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.domain.PublisherSeries;
import com.rutinim.exam.management.repository.ExamPublisherRepository;
import com.rutinim.exam.management.service.impl.ExamPublisherServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamPublisherMapper;
import com.rutinim.exam.management.web.mappers.ExamPublisherMapperImpl;
import com.rutinim.exam.management.web.mappers.PublisherSeriesMapper;
import com.rutinim.exam.management.web.model.ExamDto;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamPublisherServiceTest {

    @Mock
    private ExamPublisherRepository examPublisherRepository;

    @Spy
    private final PublisherSeriesMapper publisherSeriesMapper = Mappers.getMapper(PublisherSeriesMapper.class);
    @Spy
    private final ExamPublisherMapper examPublisherMapper = new ExamPublisherMapperImpl(publisherSeriesMapper);

    @InjectMocks
    private ExamPublisherServiceImpl examPublisherService;

    ExamPublisher examPublisher;
    ExamPublisherDto examPublisherDto;
    Exam exam;
    UUID publisherId;
    UUID examPublisherId;
    UUID examId;

    @BeforeEach
    void setUp() {
        publisherId = UUID.randomUUID();
        examPublisherId = UUID.randomUUID();
        examId = UUID.randomUUID();

        exam = Exam.builder()
                .id(examId)
                .examName("a exam name")
                .build();

        examPublisher = ExamPublisher.builder()
                .id(examPublisherId)
                .publisherId(publisherId)
                .publisherName("A simple publisher")
                .publisherSeries(new ArrayList<PublisherSeries>())
                .exam(exam)
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

    @DisplayName("Should Save Exam Publisher Successfully")
    @Test
    void shouldSaveExamPublisherSuccessfully() {
        examPublisherService.saveExamPublisher(examPublisherDto);

        verify(examPublisherRepository).save(any());
    }

    @DisplayName("Should Update Exam Publisher Successfully")
    @Test
    void shouldUpdateExamPublisherSuccessfully() {
        when(examPublisherRepository.getOne(examPublisherId)).thenReturn(examPublisher);

        ExamDto updatedExamDto = ExamDto.builder().examId(examId.toString()).examName("new exam name").build();

        ExamPublisherDto updatedExamPublisherDto = ExamPublisherDto.builder()
                .examPublisherId(examPublisherDto.getExamPublisherId())
                .publisherId(examPublisherDto.getPublisherId())
                .publisherName("different publisher name")
                .examDto(updatedExamDto)
                .build();

        examPublisherService.updateExamPublisher(updatedExamPublisherDto);

        assertThat(examPublisher.getPublisherName())
                .isEqualTo(updatedExamPublisherDto.getPublisherName())
                .isNotNull();

        assertThat(examPublisher.getExam().getExamName())
                .isEqualTo(updatedExamDto.getExamName())
                .isNotNull();

        verify(examPublisherRepository).getOne(examPublisherId);
        verify(examPublisherRepository).save(any());
    }


    @DisplayName("Should Add a Publisher Series to Exam Publisher Successfully")
    @Test
    void shouldUpdateAddPublisherSeriesToExamPublisherSuccessfully() {
        when(examPublisherRepository.getOne(examPublisherId)).thenReturn(examPublisher);

        PublisherSeriesDto publisherSeriesDto = PublisherSeriesDto.builder()
                .sequenceName("a new sequence name")
                .numberOfSequence(5)
                .build();

        examPublisherDto.getPublisherSeriesDto().add(publisherSeriesDto);

        examPublisherService.addPublisherSeries(examPublisherDto);

        assertThat(examPublisher.getPublisherSeries().get(0).getSequenceName())
                .isEqualTo(publisherSeriesDto.getSequenceName())
                .isNotNull();

        verify(examPublisherRepository).getOne(examPublisherId);
        verify(examPublisherRepository).save(any());
    }

    @DisplayName("Should Delete Exam Publisher Successfully")
    @Test
    void shouldDeleteExamPublisherSuccessfully() {
        when(examPublisherRepository.getOne(examPublisherId)).thenReturn(examPublisher);

        examPublisherService.deleteExamPublisher(examPublisherId);

        verify(examPublisherRepository).getOne(examPublisherId);
        verify(examPublisherRepository).delete(any());
    }


    @DisplayName("Should Delete Publisher Series Successfully")
    @Test
    void shouldDeletePublisherSeriesSuccessfully() {
        PublisherSeries publisherSeriesDto1 = PublisherSeries.builder().id(UUID.randomUUID()).build();
        PublisherSeries publisherSeriesDto2 = PublisherSeries.builder().id(UUID.randomUUID()).build();
        PublisherSeries publisherSeriesDto3 = PublisherSeries.builder().id(UUID.randomUUID()).build();
        PublisherSeries publisherSeriesDto4 = PublisherSeries.builder().id(UUID.randomUUID()).build();
        examPublisher.addPublisherSeries(publisherSeriesDto1);
        examPublisher.addPublisherSeries(publisherSeriesDto2);
        examPublisher.addPublisherSeries(publisherSeriesDto3);
        examPublisher.addPublisherSeries(publisherSeriesDto4);

        when(examPublisherRepository.getOne(examPublisherId)).thenReturn(examPublisher);

        ExamPublisherDto tempDto = examPublisherMapper.examPublisherToExamPublisherDto(examPublisher);

        tempDto.getPublisherSeriesDto().remove(0);

        examPublisherService.deletePublisherSeries(tempDto);

        assertThat(examPublisher.getPublisherSeries().size())
                .isEqualTo(1)
                .isNotNull();

        verify(examPublisherRepository).getOne(examPublisherId);
        verify(examPublisherRepository).save(any());
    }



}