package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.ExamTypeService;
import com.rutinim.exam.management.web.model.ExamDto;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(ExamTypeController.class)
class ExamTypeControllerTest {

    @MockBean
    private ExamTypeService examTypeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ExamTypeDto examTypeDto;
    ExamFieldDto examFieldDto;

    UUID examTypeId;
    UUID examFieldId;
    UUID examId;

    @BeforeEach
    void setUp() {
        examTypeId = UUID.randomUUID();
        examFieldId = UUID.randomUUID();
        examId = UUID.randomUUID();

        examFieldDto = ExamFieldDto.builder()
                .examFieldId(examFieldId.toString())
                .fieldName("a field name")
                .numberOfQuestions(20)
                .build();

        examTypeDto = ExamTypeDto.builder()
                .examTypeId(examTypeId.toString())
                .typeName("a type name")
                .isOnePiece(true)
                .isPreparatoryExam(true)
                .isExamFieldsChanged(false)
                .examDuration(60)
                .examId(examId)
                .examFieldsDto(Collections.singletonList(examFieldDto))
                .build();
    }

    @DisplayName("Test Listing Exam Types")
    @Test
    void whenListExamTypes_thenReturns200() throws Exception {
        given(examTypeService.listExamTypes()).willReturn(Collections.singletonList(examTypeDto));

        mockMvc.perform(get("/examtype"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.[0].examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.[0].typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.[0].examDuration", is(examTypeDto.getExamDuration())))
                .andExpect(jsonPath("$.[0].isOnePiece", is(examTypeDto.getIsOnePiece())))
                .andExpect(jsonPath("$.[0].isPreparatoryExam", is(examTypeDto.getIsPreparatoryExam())))
                .andExpect(jsonPath("$.[0].examId", is(examId.toString())))
                .andExpect(jsonPath("$.[0].examFields.length()", is(1)))
                .andExpect(jsonPath("$.[0].examFields.[0].examFieldId", is(examFieldId.toString())))
                .andExpect(jsonPath("$.[0].examFields.[0].fieldName", is(examFieldDto.getFieldName())))
                .andExpect(jsonPath("$.[0].examFields.[0].numberOfQuestions", is(examFieldDto.getNumberOfQuestions())));


        then(examTypeService).should().listExamTypes();
    }

    @DisplayName("Test Get Exam Type")
    @Test
    void whenGetExamType_thenReturns200() throws Exception {
        given(examTypeService.getExamType(examTypeId)).willReturn(examTypeDto);

        mockMvc.perform(get("/examtype/" + examTypeId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.examDuration", is(examTypeDto.getExamDuration())))
                .andExpect(jsonPath("$.isOnePiece", is(examTypeDto.getIsOnePiece())))
                .andExpect(jsonPath("$.isPreparatoryExam", is(examTypeDto.getIsPreparatoryExam())))
                .andExpect(jsonPath("$.examId", is(examId.toString())))
                .andExpect(jsonPath("$.examFields.length()", is(1)))
                .andExpect(jsonPath("$.examFields.[0].examFieldId", is(examFieldId.toString())))
                .andExpect(jsonPath("$.examFields.[0].fieldName", is(examFieldDto.getFieldName())))
                .andExpect(jsonPath("$.examFields.[0].numberOfQuestions", is(examFieldDto.getNumberOfQuestions())));


        then(examTypeService).should().getExamType(any());
    }

    @DisplayName("Test Save Exam Type")
    @Test
    void whenSaveExamType_thenReturns201() throws Exception {

        examTypeDto.setExamTypeId(null);

        mockMvc.perform(post("/examtype")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examTypeDto)))
                .andExpect(status().isCreated());

        then(examTypeService).should().saveExamType(examTypeDto);
    }

    @DisplayName("Test Update Exam Type")
    @Test
    void whenUpdateExamType_thenReturns206() throws Exception {

        examTypeDto.setExamTypeId(null);

        mockMvc.perform(put("/examtype/" + examTypeId.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examTypeDto)))
                .andExpect(status().isNoContent());

        then(examTypeService).should().updateExamType(examTypeId, examTypeDto);
    }

    @DisplayName("Test Add Exam Field to Exam Type")
    @Test
    void whenAddExamFieldToExamType_thenReturns201() throws Exception {

        examTypeDto.setExamTypeId(null);

        mockMvc.perform(post("/examtype/" + examTypeId.toString() + "/field")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examTypeDto)))
                .andExpect(status().isCreated());

        then(examTypeService).should().addExamField(examTypeId, examTypeDto);
    }

    @DisplayName("Test Deleting Exam Type")
    @Test
    void whenDeletingExamType_thenReturns200() throws Exception {

        mockMvc.perform(delete("/examtype/" + examTypeId.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        then(examTypeService).should().deleteExamType(examTypeId);
    }

}