package com.rutinim.exam.management.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutinim.exam.management.service.PublisherSeriesService;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
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
@WebMvcTest(PublisherSeriesController.class)
class PublisherSeriesControllerTest {

    @MockBean
    private PublisherSeriesService publisherSeriesService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    PublisherSeriesDto publisherSeriesDto;

    UUID publisherSeriesId;

    @BeforeEach
    void setUp() {
        publisherSeriesId = UUID.randomUUID();

        publisherSeriesDto = PublisherSeriesDto.builder()
                .publisherSeriesId(publisherSeriesId.toString())
                .sequenceName("a sequence name")
                .numberOfSequence(1)
                .build();
    }

    @DisplayName("Test Get Publisher Series")
    @Test
    void whenGetPublisherSeries_thenReturns200() throws Exception {
        given(publisherSeriesService.getPublisherSeries(publisherSeriesId)).willReturn(publisherSeriesDto);

        mockMvc.perform(get("/exam/publisher/series/" + publisherSeriesId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.publisherSeriesId", is(publisherSeriesId.toString())))
                .andExpect(jsonPath("$.sequenceName", is(publisherSeriesDto.getSequenceName())))
                .andExpect(jsonPath("$.numberOfSequence", is(publisherSeriesDto.getNumberOfSequence())));

        then(publisherSeriesService).should().getPublisherSeries(publisherSeriesId);
    }

    @DisplayName("Test Update Publisher Series")
    @Test
    void whenUpdatePublisherSeries_thenReturns206() throws Exception {

        publisherSeriesDto.setPublisherSeriesId(null);
        mockMvc.perform(put("/exam/publisher/series/" + publisherSeriesId.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(publisherSeriesDto)))
                .andExpect(status().isNoContent());

        then(publisherSeriesService).should().updatePublisherSeries(publisherSeriesId, publisherSeriesDto);
    }

}