package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.domain.ExamType;
import com.rutinim.exam.management.repository.ExamTypeRepository;
import com.rutinim.exam.management.service.impl.ExamTypeServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamFieldMapper;
import com.rutinim.exam.management.web.mappers.ExamTypeMapper;
import com.rutinim.exam.management.web.mappers.ExamTypeMapperImpl;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import com.rutinim.exam.management.web.model.ExamTypeDto;
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
class ExamTypeServiceTest {

    @Mock
    private ExamTypeRepository examTypeRepository;

    @Spy
    private final ExamFieldMapper examFieldMapper = Mappers.getMapper(ExamFieldMapper.class);

    @Spy
    private final ExamTypeMapper examTypeMapper = new ExamTypeMapperImpl(examFieldMapper);

    @InjectMocks
    private ExamTypeServiceImpl examTypeService;

    ExamType examType;
    ExamTypeDto examTypeDto;

    ExamField examField;
    ExamFieldDto examFieldDto;

    UUID examTypeId;
    UUID examFieldId;

    @BeforeEach
    void setUp(){
        examTypeId = UUID.randomUUID();
        examFieldId = UUID.randomUUID();

        examField = new ExamField();
        examField.setFieldName("a exam field name");
        examField.setId(examFieldId);

        examType = new ExamType();
        examType.setTypeName("A exam type name");
        examType.setExamDuration(60);
        examType.setIsPreparatoryExam(true);
        examType.setIsOnePiece(true);
        examType.setId(examTypeId);
        examType.addExamField(examField);

        examTypeDto = examTypeMapper.examTypeToExamTypeDto(examType);
    }

    @DisplayName("Test Should List Exam Type Successfully")
    @Test
    void testShouldListExamTypeSuccessfully() {
        when(examTypeRepository.findAll()).thenReturn(Collections.singletonList(examType));

        List<ExamTypeDto> returnedExamTypeDtos = examTypeService.listExamTypes();

        assertThat(returnedExamTypeDtos)
                .isEqualTo(Collections.singletonList(examTypeDto))
                .isNotNull();

        verify(examTypeRepository).findAll();
    }

    @DisplayName("Test Should Exam Type Getting Successfully")
    @Test
    void testShouldExamTypeReturnsSuccessfully() {
        when(examTypeRepository.findById(examTypeId)).thenReturn(Optional.ofNullable(examType));

        ExamTypeDto returnedExamTypeDto = examTypeService.getExamType(examTypeId);

        assertThat(returnedExamTypeDto)
                .isEqualTo(examTypeDto)
                .isNotNull();

        verify(examTypeRepository).findById(examTypeId);
    }

    @DisplayName("Test Should Saving Exam Type Successfully")
    @Test
    void testShouldSavingExamTypeSuccessfully() {
        ExamTypeDto tempExamTypeDto = new ExamTypeDto();
        tempExamTypeDto.setTypeName("a name");

        examTypeService.saveExamType(tempExamTypeDto);

        verify(examTypeRepository).save(any());
    }

    @DisplayName("Test Should Adding a Exam Field To Exam Type Successfully")
    @Test
    void testShouldAddingExamFieldSuccessfully() {
        ExamFieldDto tempExamFieldDto = ExamFieldDto.builder()
                .fieldName("a new field name")
                .build();

        ExamTypeDto tempExamTypeDto = ExamTypeDto.builder()
                .examFieldsDto(Collections.singletonList(tempExamFieldDto))
                .build();

        when(examTypeRepository.getOne(examTypeId)).thenReturn(examType);

        examTypeService.addExamField(examTypeId, tempExamTypeDto);

        assertThat(examType.getExamFields().size())
                .isEqualTo(2)
                .isNotNull();

        verify(examTypeRepository).save(any());
    }

    @DisplayName("Test Should Updating a Exam Type Successfully")
    @Test
    void testShouldUpdatingExamTypeSuccessfully() {

        examTypeDto.setTypeName("a different name!");

        when(examTypeRepository.getOne(examTypeId)).thenReturn(examType);

        examTypeService.updateExamType(examTypeId, examTypeDto);

        assertThat(examType.getTypeName())
                .isEqualTo("a different name!")
                .isNotNull();

        verify(examTypeRepository).save(any());
    }

    @DisplayName("Test Should Deleting(Empty Array) a Exam Field Successfully")
    @Test
    void testShouldDeletingEmptyArrayExamFieldSuccessfully() {

        examTypeDto.getExamFieldsDto().remove(0);

        when(examTypeRepository.getOne(examTypeId)).thenReturn(examType);

        examTypeService.updateExamType(examTypeId, examTypeDto);

        assertThat(examType.getExamFields().size())
                .isEqualTo(0)
                .isNotNull();

        verify(examTypeRepository).save(any());
    }

    @DisplayName("Test Should Deleting a Exam Field Successfully")
    @Test
    void testShouldDeletingNotEmptyArrayExamFieldSuccessfully() {

        ExamField examField2 = new ExamField();
        examField2.setFieldName("a exam field name");
        examField2.setId(UUID.randomUUID());

        examType.addExamField(examField2);
        ExamTypeDto tempExamTypeDto = examTypeMapper.examTypeToExamTypeDto(examType);

        tempExamTypeDto.getExamFieldsDto().remove(0);

        when(examTypeRepository.getOne(examTypeId)).thenReturn(examType);

        examTypeService.updateExamType(examTypeId, tempExamTypeDto);

        assertThat(examType.getExamFields().size())
                .isEqualTo(1)
                .isNotNull();

        verify(examTypeRepository).save(any());
    }

    @DisplayName("Test Should Deleting Exam Type Successfully")
    @Test
    void testShouldDeletingExamTypeSuccessfully() {

        when(examTypeRepository.getOne(examTypeId)).thenReturn(examType);

        examTypeService.deleteExamType(examTypeId);

        verify(examTypeRepository).delete(any());
    }

}