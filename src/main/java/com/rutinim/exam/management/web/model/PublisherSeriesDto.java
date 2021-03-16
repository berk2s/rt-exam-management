package com.rutinim.exam.management.web.model;

import lombok.*;

import javax.validation.constraints.Null;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherSeriesDto {

    @Null
    private String publisherSeriesId;

    private String sequenceName;

    private Integer numberOfSequence;

    private UUID examPublisherId;

}
