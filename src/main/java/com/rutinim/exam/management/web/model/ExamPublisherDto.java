package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamPublisherDto {

    @Null
    private String examPublisherId;

    @NotBlank
    private String publisherId;

    @NotBlank
    private String publisherName;

    @NotNull
    private Integer numberOfSeries;

    @NotBlank
    private String examImage;

    private ExamTypeDto examTypeDto;

    private List<PublisherSeriesDto> publisherSeriesDto;

    @NotNull
    private Boolean isPublisherSeriesChanged;

    @NotNull
    private UUID examTypeId;

}
