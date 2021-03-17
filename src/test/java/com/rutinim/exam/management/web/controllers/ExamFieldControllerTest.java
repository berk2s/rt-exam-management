package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.ExamFieldService;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import com.rutinim.exam.management.web.model.LessonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(ExamFieldController.class)
class ExamFieldControllerTest {

    @MockBean
    private ExamFieldService examFieldService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ExamFieldDto examFieldDto;
    ExamTypeDto examTypeDto;
    LessonDto lessonDto;

    UUID examFieldId;
    UUID lessonId;
    UUID examTypeId;

    @BeforeEach
    void setUp() {
        examFieldId = UUID.randomUUID();
        lessonId = UUID.randomUUID();
        examTypeId = UUID.randomUUID();

        examTypeDto = ExamTypeDto.builder()
                .typeName("a type name")
                .isOnePiece(true)
                .examTypeId(examTypeId.toString())
                .examDuration(60)
                .isPreparatoryExam(true)
                .build();

        lessonDto = LessonDto.builder()
                .lessonId(lessonId.toString())
                .lessonName("a lesson name")
                .build();

        examFieldDto = ExamFieldDto.builder()
                .examFieldId(examFieldId.toString())
                .fieldName("a field name")
                .numberOfQuestions(60)
                .isChanged(false)
                .examType(examTypeDto)
                .lesson(lessonDto)
                .lessonId(lessonId)
                .build();
    }

    @DisplayName("Test Get Exam Field")
    @Test
    void whenGetExamField_thenReturns200() throws Exception {
        given(examFieldService.getExamField(examFieldId)).willReturn(examFieldDto);

        mockMvc.perform(get("/exam/field/" + examFieldId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.examFieldId", is(examFieldId.toString())))
                .andExpect(jsonPath("$.fieldName", is(examFieldDto.getFieldName())))
                .andExpect(jsonPath("$.numberOfQuestions", is(examFieldDto.getNumberOfQuestions())))
                .andExpect(jsonPath("$.lessonId", is(lessonId.toString())))
                .andExpect(jsonPath("$.lesson.lessonId", is(lessonId.toString())))
                .andExpect(jsonPath("$.lesson.lessonName", is(lessonDto.getLessonName())))
                .andExpect(jsonPath("$.examType.examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.examType.typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.examType.isOnePiece", is(examTypeDto.getIsOnePiece())))
                .andExpect(jsonPath("$.examType.examDuration", is(examTypeDto.getExamDuration())))
                .andExpect(jsonPath("$.examType.isPreparatoryExam", is(examTypeDto.getIsPreparatoryExam())));

        then(examFieldService).should().getExamField(examFieldId);
    }

    @DisplayName("Test Update Exam Field")
    @Test
    void whenUpdateExamField_thenReturns206() throws Exception {

        examFieldDto.setExamFieldId(null);
        mockMvc.perform(put("/exam/field/" + examFieldId.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examFieldDto)))
                .andExpect(status().isNoContent());

        then(examFieldService).should().updateExamField(examFieldId, examFieldDto);
    }

}