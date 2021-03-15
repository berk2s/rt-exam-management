package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.domain.PublisherSeries;
import com.rutinim.exam.management.repository.PublisherSeriesRepository;
import com.rutinim.exam.management.service.impl.PublisherSeriesServiceImpl;
import com.rutinim.exam.management.web.mappers.PublisherSeriesMapper;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PublisherSeriesServiceTest {

    @Mock
    private PublisherSeriesRepository publisherSeriesRepository;

    @Spy
    private final PublisherSeriesMapper publisherSeriesMapper = Mappers.getMapper(PublisherSeriesMapper.class);

    @InjectMocks
    private PublisherSeriesServiceImpl publisherSeriesService;

    PublisherSeries publisherSeries;
    PublisherSeriesDto publisherSeriesDto;

    @BeforeEach
    void setUp() {
        publisherSeries = PublisherSeries.builder()
                .id(UUID.randomUUID())
                .numberOfSequence(1)
                .sequenceName("a sequence name")
                .build();


        publisherSeriesDto = publisherSeriesMapper.publisherSeriesToPublisherSeriesDto(publisherSeries);

    }

    @DisplayName("Test Get Publisher Series")
    @Test
    void shouldPublisherSeriesReturnsSuccessfully() {
        when(publisherSeriesRepository.findById(publisherSeries.getId())).thenReturn(Optional.of(publisherSeries));

        PublisherSeriesDto returnedPublisherSeriesDto = publisherSeriesService.getPublisherSeries(publisherSeries.getId());

        assertThat(returnedPublisherSeriesDto)
                .isEqualTo(publisherSeriesDto)
                .isNotNull();

        verify(publisherSeriesRepository).findById(any());
    }

    @DisplayName("Test Update Publisher Series")
    @Test
    void shouldUpdatePublisherSeriesSuccessfully() {
        when(publisherSeriesRepository.getOne(publisherSeries.getId())).thenReturn(publisherSeries);

        PublisherSeriesDto _publisherSeriesDto = PublisherSeriesDto.builder()
                .sequenceName("a sequence name")
                .build();

        publisherSeriesService.updatePublisherSeries(publisherSeriesDto);

        assertThat(publisherSeries.getSequenceName())
                .isEqualTo(_publisherSeriesDto.getSequenceName())
                .isNotNull();

        verify(publisherSeriesRepository).save(any());

    }

}