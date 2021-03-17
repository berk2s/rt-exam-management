package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.ExamFieldService;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam/field/{examFieldId}")
public class ExamFieldController {

    private final ExamFieldService examFieldService;

    @GetMapping
    public ResponseEntity<ExamFieldDto> getExamField(@PathVariable UUID examFieldId) {
        return new ResponseEntity<>(examFieldService.getExamField(examFieldId), HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExamField(@PathVariable UUID examFieldId,
                                @Valid @RequestBody ExamFieldDto examFieldDto) {
        examFieldService.updateExamField(examFieldId, examFieldDto);
    }

}
