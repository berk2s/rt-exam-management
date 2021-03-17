package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.PublisherSeriesService;
import com.rutinim.exam.management.web.model.PublisherSeriesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/publisher/series/{publisherSeriesId}")
public class PublisherSeriesController {

    private final PublisherSeriesService publisherSeriesService;

    @GetMapping
    public ResponseEntity<PublisherSeriesDto> getPublisherSeries(@PathVariable UUID publisherSeriesId) {
        return new ResponseEntity<>(publisherSeriesService.getPublisherSeries(publisherSeriesId), HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublisherSeries(@PathVariable UUID publisherSeriesId,
                                      @Valid @RequestBody PublisherSeriesDto publisherSeriesDto) {
        publisherSeriesService.updatePublisherSeries(publisherSeriesId, publisherSeriesDto);
    }

}
