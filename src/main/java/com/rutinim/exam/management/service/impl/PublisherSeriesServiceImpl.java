package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.PublisherSeries;
import com.rutinim.exam.management.repository.PublisherSeriesRepository;
import com.rutinim.exam.management.service.PublisherSeriesService;
import com.rutinim.exam.management.web.exception.PublisherSeriesNotFoundException;
import com.rutinim.exam.management.web.mappers.PublisherSeriesMapper;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublisherSeriesServiceImpl implements PublisherSeriesService {

    private final PublisherSeriesRepository publisherSeriesRepository;
    private final PublisherSeriesMapper publisherSeriesMapper;

    @Override
    public PublisherSeriesDto getPublisherSeries(UUID publisherSeriesId) {
        Optional<PublisherSeries> publisherSeriesOptional = publisherSeriesRepository.findById(publisherSeriesId);

        if(publisherSeriesOptional.isEmpty())
            throw new PublisherSeriesNotFoundException("Publisher Series doesn't exists");

        return publisherSeriesMapper.publisherSeriesToPublisherSeriesDto(publisherSeriesOptional.get());
    }

    @Override
    public void updatePublisherSeries(UUID publisherSeriesId, PublisherSeriesDto publisherSeriesDto) {
        PublisherSeries publisherSeries = publisherSeriesRepository.getOne(publisherSeriesId);

        publisherSeries.setNumberOfSequence(publisherSeriesDto.getNumberOfSequence());
        publisherSeries.setSequenceName(publisherSeriesDto.getSequenceName());

        publisherSeriesRepository.save(publisherSeries);
    }


}
