package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.repository.ExamRepository;
import com.rutinim.exam.management.service.impl.ExamServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamMapper;
import com.rutinim.exam.management.web.model.ExamDto;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamServiceTest {

    @Mock
    private ExamRepository examRepository;

    @Spy
    private final ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);

    @InjectMocks
    private ExamServiceImpl examService;

    Exam exam;
    ExamDto examDto;
    UUID examId;

    @BeforeEach
    void setUp() {
        examId = UUID.randomUUID();

        exam = new Exam();
        exam.setId(examId);
        exam.setExamName("A simple exam name");

        examDto = examMapper.examToExamDto(exam);
    }

    @DisplayName("Should Exam Returns Successfully")
    @Test
    void testShouldExamReturnsSuccessfully() {
        when(examRepository.findById(examId)).thenReturn(Optional.of(exam));

        ExamDto returnedExamDto = examService.getExam(examId);

        assertThat(returnedExamDto)
                .isEqualTo(examDto)
                .isNotNull();

        verify(examRepository).findById(examId);
    }

    @DisplayName("Should Exam List Returns Successfully")
    @Test
    void testShouldExamListReturnsSuccessfully() {
        when(examRepository.findAll()).thenReturn(Collections.singletonList(exam));

        List<ExamDto> returnedExamDtos = examService.listExams();

        assertThat(returnedExamDtos.get(0))
                .isEqualTo(examDto)
                .isNotNull();

        verify(examRepository).findAll();
    }

    @DisplayName("Should Save Exam Successfully")
    @Test
    void testShouldSaveExamSuccessfully() {

        examService.saveExam(examDto);

        verify(examRepository).save(any());
    }

    @DisplayName("Should Update Exam Successfully")
    @Test
    void testShouldUpdateExamSuccessfully() {
        when(examRepository.getOne(examId)).thenReturn(exam);

        ExamDto tempExamDto = ExamDto.builder()
                .examId(examId.toString())
                .examName("a new exam")
                .build();

        examService.updateExam(tempExamDto);

        assertThat(exam.getExamName())
                .isEqualTo(tempExamDto.getExamName())
                .isNotNull();

        verify(examRepository).getOne(examId);
        verify(examRepository).save(any());
    }

    @DisplayName("Should Delete Exam Successfully")
    @Test
    void testShouldDeleteExamSuccessfully() {
        when(examRepository.getOne(examId)).thenReturn(exam);

        examService.deleteExam(examId);


        verify(examRepository).getOne(any());
        verify(examRepository).delete(any());
    }

}