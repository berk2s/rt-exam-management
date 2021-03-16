package com.rutinim.exam.management.web.mappers;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class}, uses = {PublisherSeriesMapper.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ExamPublisherMapper {

    @Mappings({
            @Mapping(target = "publisherId", expression = "java( UUID.fromString(examPublisherDto.getPublisherId()) )"),
            @Mapping(target = "examType", source = "examTypeDto"),
            @Mapping(target = "publisherSeries", source = "publisherSeriesDto"),
    })
    ExamPublisher examPublisherDtoToExamPublisher(ExamPublisherDto examPublisherDto);

    @Mappings({
            @Mapping(target = "examPublisherId", expression = "java( examPublisher.getId().toString() )"),
            @Mapping(target = "publisherId", expression = "java( examPublisher.getPublisherId().toString() )"),
            @Mapping(target = "examTypeDto", source = "examType"),
            @Mapping(target = "publisherSeriesDto", source = "publisherSeries"),
            @Mapping(target = "examTypeId", source = "examType.id"),
    })
    ExamPublisherDto examPublisherToExamPublisherDto(ExamPublisher examPublisher);

    @Mappings({
            @Mapping(target = "examPublisherId", expression = "java( examPublisher.getId().toString() )"),
            @Mapping(target = "publisherId", expression = "java( examPublisher.getPublisherId().toString() )"),
            @Mapping(target = "examTypeDto", source = "examType"),
            @Mapping(target = "publisherSeriesDto", source = "publisherSeries"),
            @Mapping(target = "examTypeId", source = "examType.id"),
    })
    List<ExamPublisherDto> examPublisherToExamPublisherDto(List<ExamPublisher> examPublisher);

}
