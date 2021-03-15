package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.repository.PublisherSeriesRepository;
import com.rutinim.exam.management.service.PublisherSeriesService;
import com.rutinim.exam.management.web.mappers.PublisherSeriesMapper;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublisherSeriesServiceImpl implements PublisherSeriesService {

    private final PublisherSeriesRepository publisherSeriesRepository;
    private final PublisherSeriesMapper publisherSeriesMapper;

    @Override
    public PublisherSeriesDto getPublisherSeries(UUID publisherSeriesId) {
        return null;
    }

    @Override
    public void updatePublisherSeries(UUID examPublisherId, PublisherSeriesDto publisherSeriesDto) {

    }

    @Override
    public void deletePublisherSeries(UUID publisherSeriesId) {

    }
}
