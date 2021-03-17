package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.ExamPublisherService;
import com.rutinim.exam.management.web.model.ExamPublisherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/publisher")
public class ExamPublisherController {

    private final ExamPublisherService examPublisherService;

    @GetMapping
    public ResponseEntity<List<ExamPublisherDto>> listExamPublishers() {
        return new ResponseEntity<>(examPublisherService.listExamPublishers(), HttpStatus.OK);
    }

    @GetMapping("/{examPublisherId}")
    public ResponseEntity<ExamPublisherDto> getExamPublisher(@PathVariable UUID examPublisherId) {
        return new ResponseEntity<>(examPublisherService.getExamPublisher(examPublisherId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExamPublisher(@Valid @RequestBody ExamPublisherDto examPublisherDto) {
        examPublisherService.saveExamPublisher(examPublisherDto);
    }

    @PutMapping("/{examPublisherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExamPublisher(@PathVariable UUID examPublisherId,
                                    @Valid @RequestBody ExamPublisherDto examPublisherDto) {
        examPublisherService.updateExamPublisher(examPublisherId, examPublisherDto);
    }

    @PostMapping("/{examPublisherId}/series")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPublisherSeries(@PathVariable UUID examPublisherId,
                                   @Valid @RequestBody ExamPublisherDto examPublisherDto) {
        examPublisherService.addPublisherSeries(examPublisherId, examPublisherDto);
    }

    @DeleteMapping("/{examPublisherId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExamPublisher(@PathVariable UUID examPublisherId) {
        examPublisherService.deleteExamPublisher(examPublisherId);
    }
}
