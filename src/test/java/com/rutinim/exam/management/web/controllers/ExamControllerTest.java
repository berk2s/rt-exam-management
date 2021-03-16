package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.ExamService;
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

@WebMvcTest(ExamController.class)
class ExamControllerTest {

    @MockBean
    private ExamService examService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ExamDto examDto;
    ExamTypeDto examTypeDto;
    ExamFieldDto examFieldDto;

    UUID examId;
    UUID examTypeId;
    UUID examFieldId;

    @BeforeEach
    void setUp() {
        examId = UUID.randomUUID();
        examTypeId = UUID.randomUUID();
        examFieldId = UUID.randomUUID();

        examFieldDto = ExamFieldDto.builder()
                .examFieldId(examFieldId.toString())
                .fieldName("a field name")
                .numberOfQuestions(60)
                .build();


        examTypeDto = ExamTypeDto.builder()
                .examTypeId(examTypeId.toString())
                .examFieldsDto(Collections.singletonList(examFieldDto))
                .examId(examId)
                .examDuration(60)
                .isPreparatoryExam(true)
                .typeName("a type name")
                .isOnePiece(true)
                .build();

        examDto = ExamDto.builder()
                .examId(examId.toString())
                .examName("a simple exam name")
                .examTypeDtos(Collections.singletonList(examTypeDto))
                .build();
    }

    @DisplayName("Test Listing Exams")
    @Test
    void whenListExams_thenReturns200() throws Exception {
        given(examService.listExams()).willReturn(Collections.singletonList(examDto));

        mockMvc.perform(get("/exam"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.[0].examId", is(examId.toString())))
                .andExpect(jsonPath("$.[0].examName", is(examDto.getExamName())))
                .andExpect(jsonPath("$.[0].examTypes.length()", is(1)))
                .andExpect(jsonPath("$.[0].examTypes.[0].examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.[0].examTypes.[0].typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.[0].examTypes.[0].examDuration", is(examTypeDto.getExamDuration())))
                .andExpect(jsonPath("$.[0].examTypes.[0].isOnePiece", is(examTypeDto.getIsOnePiece())))
                .andExpect(jsonPath("$.[0].examTypes.[0].isPreparatoryExam", is(examTypeDto.getIsPreparatoryExam())))
                .andExpect(jsonPath("$.[0].examTypes.[0].examId", is(examId.toString())))
                .andExpect(jsonPath("$.[0].examTypes.[0].examFields.length()", is(1)))
                .andExpect(jsonPath("$.[0].examTypes.[0].examFields.[0].examFieldId", is(examFieldId.toString())))
                .andExpect(jsonPath("$.[0].examTypes.[0].examFields.[0].fieldName", is(examFieldDto.getFieldName())))
                .andExpect(jsonPath("$.[0].examTypes.[0].examFields.[0].numberOfQuestions", is(examFieldDto.getNumberOfQuestions())));

        then(examService).should().listExams();
    }


    @DisplayName("Test Get Exam")
    @Test
    void whenGetExams_thenReturns200() throws Exception {
        given(examService.getExam(examId)).willReturn(examDto);

        mockMvc.perform(get("/exam/"+examId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.examId", is(examId.toString())))
                .andExpect(jsonPath("$.examName", is(examDto.getExamName())))
                .andExpect(jsonPath("$.examTypes.length()", is(1)))
                .andExpect(jsonPath("$.examTypes.[0].examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.examTypes.[0].typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.examTypes.[0].examDuration", is(examTypeDto.getExamDuration())))
                .andExpect(jsonPath("$.examTypes.[0].isOnePiece", is(examTypeDto.getIsOnePiece())))
                .andExpect(jsonPath("$.examTypes.[0].isPreparatoryExam", is(examTypeDto.getIsPreparatoryExam())))
                .andExpect(jsonPath("$.examTypes.[0].examId", is(examId.toString())))
                .andExpect(jsonPath("$.examTypes.[0].examFields.length()", is(1)))
                .andExpect(jsonPath("$.examTypes.[0].examFields.[0].examFieldId", is(examFieldId.toString())))
                .andExpect(jsonPath("$.examTypes.[0].examFields.[0].fieldName", is(examFieldDto.getFieldName())))
                .andExpect(jsonPath("$.examTypes.[0].examFields.[0].numberOfQuestions", is(examFieldDto.getNumberOfQuestions())));

        then(examService).should().getExam(examId);
    }

    @DisplayName("Test Create a Exam")
    @Test
    void whenValidExam_thenReturns201() throws Exception {

        examDto.setExamId(null);

        mockMvc.perform(post("/exam")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(examDto)))
            .andExpect(status().isCreated());

        then(examService).should().saveExam(examDto);
    }

    @DisplayName("Test Update a Exam")
    @Test
    void whenUpdateExam_thenReturns206() throws Exception {

        examDto.setExamId(null);

        mockMvc.perform(put("/exam/" + examId.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(examDto)))
            .andExpect(status().isNoContent());

        then(examService).should().updateExam(examId, examDto);
    }

    @DisplayName("Test Delete a Exam")
    @Test
    void whenDeleteExam_thenReturns200() throws Exception {

        mockMvc.perform(delete("/exam/" + examId.toString()))
            .andExpect(status().isOk());

        then(examService).should().deleteExam(examId);
    }

}