package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.LessonService;
import com.rutinim.exam.management.web.model.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/publisher/series/lesson/{lessonId}")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<LessonDto> getLesson(@PathVariable UUID lessonId) {
        return new ResponseEntity<>(lessonService.getLesson(lessonId), HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void updateLesson(@PathVariable UUID lessonId,
                              @Valid @RequestBody LessonDto lessonDto) {
        lessonService.updateLesson(lessonId, lessonDto);
    }

}
