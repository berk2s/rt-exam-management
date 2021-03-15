package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.ExamPublisherDto;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;

import java.util.UUID;

public interface PublisherSeriesService {

    PublisherSeriesDto getPublisherSeries(UUID publisherSeriesId);

    void updatePublisherSeries(UUID examPublisherId, PublisherSeriesDto publisherSeriesDto);

    void deletePublisherSeries(UUID publisherSeriesId);

}
