package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamPublisherDto {

    @Null
    private String examPublisherId;

    private String publisherId;

    private String publisherName;

    private Integer numberOfSeries;

    private String examImage;

    private ExamDto examDto;

    private List<PublisherSeriesDto> publisherSeriesDto;

}
