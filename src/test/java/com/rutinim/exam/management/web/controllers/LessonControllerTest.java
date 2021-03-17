package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.LessonService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(LessonController.class)
class LessonControllerTest {

    @MockBean
    private LessonService lessonService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    LessonDto lessonDto;

    UUID lessonId;

    @BeforeEach
    void setUp() {
        lessonId = UUID.randomUUID();

        lessonDto = LessonDto.builder()
                .lessonId(lessonId.toString())
                .lessonName("a lesson name")
                .build();

    }

    @DisplayName("Test Get Lesson")
    @Test
    void whenGetLesson_thenReturns200() throws Exception {
        given(lessonService.getLesson(lessonId)).willReturn(lessonDto);

        mockMvc.perform(get("/exam/publisher/series/lesson/" + lessonId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lessonId", is(lessonId.toString())))
                .andExpect(jsonPath("$.lessonName", is(lessonDto.getLessonName())));

        then(lessonService).should().getLesson(lessonId);
    }

    @DisplayName("Test Update Lesson")
    @Test
    void whenUpdateLesson_thenReturns206() throws Exception {

        lessonDto.setLessonId(null);

        mockMvc.perform(put("/exam/publisher/series/lesson/" + lessonId.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(lessonDto)))
                .andExpect(status().isNoContent());

        then(lessonService).should().updateLesson(lessonId, lessonDto);
    }
}