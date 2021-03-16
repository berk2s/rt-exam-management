package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.repository.ExamFieldRepository;
import com.rutinim.exam.management.service.impl.ExamFieldServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamFieldMapper;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamFieldServiceTest {

    @Mock
    private ExamFieldRepository examFieldRepository;

    @Spy
    private ExamFieldMapper examFieldMapper = Mappers.getMapper(ExamFieldMapper.class);

    @InjectMocks
    private ExamFieldServiceImpl examFieldService;

    ExamField examField;
    ExamFieldDto examFieldDto;
    UUID examFieldId;

    @BeforeEach
    void setUp(){
        examFieldId = UUID.randomUUID();
        examField = new ExamField();
        examField.setFieldName("a simple exam field anme");
        examField.setNumberOfQuestions(20);
        examField.setId(examFieldId);

        examFieldDto = examFieldMapper.examFieldToExamFieldDto(examField);
    }

    @DisplayName("Test Should Exam Field Returns Successfully")
    @Test
    void testShouldExamFieldReturnSuccessfully() {
        when(examFieldRepository.findById(examFieldId)).thenReturn(Optional.ofNullable(examField));

        ExamFieldDto returnedExamFieldDto = examFieldService.getExamField(examFieldId);

        assertThat(returnedExamFieldDto)
                .isEqualTo(examFieldDto)
                .isNotNull();

        verify(examFieldRepository).findById(examFieldId);
    }

    @DisplayName("Test Should Update Exam Field Successfully")
    @Test
    void testShouldUpdateExamFieldSuccessfully() {
        String newFieldName = "an updated field name";
        examFieldDto.setFieldName(newFieldName);
        when(examFieldRepository.getOne(examFieldId)).thenReturn(examField);

        examFieldService.updateExamField(examFieldId, examFieldDto);

        assertThat(examField.getFieldName())
                .isEqualTo(newFieldName)
                .isNotNull();

        verify(examFieldRepository).save(any());
    }

}