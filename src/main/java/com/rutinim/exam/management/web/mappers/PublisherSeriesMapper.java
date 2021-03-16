package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.PublisherSeries;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface PublisherSeriesMapper {

    @Mappings({
            @Mapping(target = "examPublisher.id", source = "examPublisherId")
    })
    PublisherSeries publisherSeriesDtoToPublisherSeries(PublisherSeriesDto publisherSeriesDto);

    @Mappings({
            @Mapping(target = "publisherSeriesId", expression = "java( publisherSeries.getId().toString() )"),
            @Mapping(target = "examPublisherId", source = "examPublisher.id")
    })
    PublisherSeriesDto publisherSeriesToPublisherSeriesDto(PublisherSeries publisherSeries);


    @Mappings({
            @Mapping(target = "publisherSeriesId", expression = "java( publisherSeries.getId().toString() )"),
            @Mapping(target = "examPublisherId", source = "examPublisher.id")
    })
    List<PublisherSeriesDto> publisherSeriesToPublisherSeriesDto(List<PublisherSeries> publisherSeries);


}
