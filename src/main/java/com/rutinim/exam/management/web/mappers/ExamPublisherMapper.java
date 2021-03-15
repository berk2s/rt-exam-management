package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface ExamPublisherMapper {

    @Mappings({
            @Mapping(target = "id", expression = "java( UUID.fromString(examPublisherDto.getExamPublisherId()) )"),
            @Mapping(target = "publisherId", expression = "java( UUID.fromString(examPublisherDto.getPublisherId()) )"),
            @Mapping(target = "exam", source = "examDto"),
            @Mapping(target = "publisherSeries", source = "publisherSeriesDto"),
    })
    ExamPublisher examPublisherDtoToExamPublisher(ExamPublisherDto examPublisherDto);

    @Mappings({
            @Mapping(target = "examPublisherId", expression = "java( examPublisher.getId().toString() )"),
            @Mapping(target = "publisherId", expression = "java( examPublisher.getPublisherId().toString() )"),
            @Mapping(target = "examDto", source = "exam"),
            @Mapping(target = "publisherSeriesDto", source = "publisherSeries"),
    })
    ExamPublisherDto examPublisherToExamPublisherDto(ExamPublisher examPublisher);

    @Mappings({
            @Mapping(target = "examPublisherId", expression = "java( examPublisher.getId().toString() )"),
            @Mapping(target = "publisherId", expression = "java( examPublisher.getPublisherId().toString() )"),
            @Mapping(target = "examDto", source = "exam"),
            @Mapping(target = "publisherSeriesDto", source = "publisherSeries"),
    })
    List<ExamPublisherDto> examPublisherToExamPublisherDto(List<ExamPublisher> examPublisher);

}
