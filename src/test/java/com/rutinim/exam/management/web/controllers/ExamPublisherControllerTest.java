package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.ExamPublisherService;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
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

@WebMvcTest(ExamPublisherController.class)
class ExamPublisherControllerTest {

    @MockBean
    private ExamPublisherService examPublisherService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    ExamPublisherDto examPublisherDto;
    ExamTypeDto examTypeDto;
    PublisherSeriesDto publisherSeriesDto;

    UUID examPublisherId;
    UUID publisherId;
    UUID examTypeId;
    UUID publisherSeriesId;

    @BeforeEach
    void setUp() {
        examPublisherId = UUID.randomUUID();
        publisherId = UUID.randomUUID();
        examTypeId = UUID.randomUUID();
        publisherSeriesId = UUID.randomUUID();

        examTypeDto = ExamTypeDto.builder()
                .examTypeId(examTypeId.toString())
                .typeName("a type name")
                .build();

        publisherSeriesDto = PublisherSeriesDto.builder()
                .publisherSeriesId(publisherSeriesId.toString())
                .numberOfSequence(1)
                .sequenceName("a sequence")
                .build();

        examPublisherDto = ExamPublisherDto.builder()
                .examPublisherId(examPublisherId.toString())
                .isPublisherSeriesChanged(false)
                .examImage("a img")
                .publisherName("a publisher name")
                .publisherId(publisherId.toString())
                .numberOfSeries(1)
                .examTypeId(examTypeId)
                .examTypeDto(examTypeDto)
                .publisherSeriesDto(Collections.singletonList(publisherSeriesDto))
                .build();
    }

    @DisplayName("Test Listing Exam Publisher")
    @Test
    void whenListExamPublisher_thenReturns200() throws Exception {
        given(examPublisherService.listExamPublishers()).willReturn(Collections.singletonList(examPublisherDto));

        mockMvc.perform(get("/exam/publisher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$.[0].examPublisherId", is(examPublisherId.toString())))
                .andExpect(jsonPath("$.[0].publisherId", is(publisherId.toString())))
                .andExpect(jsonPath("$.[0].publisherName", is(examPublisherDto.getPublisherName())))
                .andExpect(jsonPath("$.[0].numberOfSeries", is(examPublisherDto.getNumberOfSeries())))
                .andExpect(jsonPath("$.[0].examImage", is(examPublisherDto.getExamImage())))
                .andExpect(jsonPath("$.[0].examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.[0].examType.examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.[0].examType.typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.[0].publisherSeries.length()", is(1)))
                .andExpect(jsonPath("$.[0].publisherSeries.[0].publisherSeriesId", is(publisherSeriesId.toString())))
                .andExpect(jsonPath("$.[0].publisherSeries.[0].sequenceName", is(publisherSeriesDto.getSequenceName())))
                .andExpect(jsonPath("$.[0].publisherSeries.[0].numberOfSequence", is(publisherSeriesDto.getNumberOfSequence())));

        then(examPublisherService).should().listExamPublishers();
    }

    @DisplayName("Test Get Exam Publisher")
    @Test
    void whenGetExamPublisher_thenReturns200() throws Exception {
        given(examPublisherService.getExamPublisher(examPublisherId)).willReturn(examPublisherDto);

        mockMvc.perform(get("/exam/publisher/" + examPublisherId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.examPublisherId", is(examPublisherId.toString())))
                .andExpect(jsonPath("$.publisherId", is(publisherId.toString())))
                .andExpect(jsonPath("$.publisherName", is(examPublisherDto.getPublisherName())))
                .andExpect(jsonPath("$.numberOfSeries", is(examPublisherDto.getNumberOfSeries())))
                .andExpect(jsonPath("$.examImage", is(examPublisherDto.getExamImage())))
                .andExpect(jsonPath("$.examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.examType.examTypeId", is(examTypeId.toString())))
                .andExpect(jsonPath("$.examType.typeName", is(examTypeDto.getTypeName())))
                .andExpect(jsonPath("$.publisherSeries.length()", is(1)))
                .andExpect(jsonPath("$.publisherSeries.[0].publisherSeriesId", is(publisherSeriesId.toString())))
                .andExpect(jsonPath("$.publisherSeries.[0].sequenceName", is(publisherSeriesDto.getSequenceName())))
                .andExpect(jsonPath("$.publisherSeries.[0].numberOfSequence", is(publisherSeriesDto.getNumberOfSequence())));

        then(examPublisherService).should().getExamPublisher(examPublisherId);
    }

    @DisplayName("Test Save Exam Publisher")
    @Test
    void whenSaveExamPublisher_thenReturns201() throws Exception {
        examPublisherDto.setExamPublisherId(null);
        mockMvc.perform(post("/exam/publisher/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examPublisherDto)))
                .andExpect(status().isCreated());

        then(examPublisherService).should().saveExamPublisher(examPublisherDto);
    }

    @DisplayName("Test Update Exam Publisher")
    @Test
    void whenUpdateExamPublisher_thenReturns206() throws Exception {
        examPublisherDto.setExamPublisherId(null);
        mockMvc.perform(put("/exam/publisher/" + examPublisherId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examPublisherDto)))
                .andExpect(status().isNoContent());

        then(examPublisherService).should().updateExamPublisher(examPublisherId, examPublisherDto);
    }

    @DisplayName("Test Add a Publisher Series To Exam Publisher")
    @Test
    void whenAddPublisherSeriesToExamPublisher_thenReturns201() throws Exception {
        examPublisherDto.setIsPublisherSeriesChanged(true);
        examPublisherDto.setExamPublisherId(null);

        mockMvc.perform(post("/exam/publisher/" + examPublisherId + "/series")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(examPublisherDto)))
                .andExpect(status().isCreated());

        then(examPublisherService).should().addPublisherSeries(examPublisherId, examPublisherDto);
    }

    @DisplayName("Test Delete Exam Publisher")
    @Test
    void whenDeleteExamPublisher_thenReturns200() throws Exception {

        mockMvc.perform(delete("/exam/publisher/" + examPublisherId ))
                .andExpect(status().isOk());

        then(examPublisherService).should().deleteExamPublisher(examPublisherId);
    }
}