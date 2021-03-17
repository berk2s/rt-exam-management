package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.ExamTypeService;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/examtype")
public class ExamTypeController {

    private final ExamTypeService examTypeService;

    @GetMapping
    public ResponseEntity<List<ExamTypeDto>> listExamTypes() {
        return new ResponseEntity<>(examTypeService.listExamTypes(), HttpStatus.OK);
    }

    @GetMapping("/{examTypeId}")
    public ResponseEntity<ExamTypeDto> getExamType(@PathVariable UUID examTypeId) {
        return new ResponseEntity<>(examTypeService.getExamType(examTypeId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExamType(@Valid @RequestBody ExamTypeDto examTypeDto) {
        examTypeService.saveExamType(examTypeDto);
    }

    @PutMapping("/{examTypeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExamType(@PathVariable UUID examTypeId,
                               @Valid @RequestBody ExamTypeDto examTypeDto) {
        examTypeService.updateExamType(examTypeId, examTypeDto);
    }

    @PostMapping("/{examTypeId}/field")
    @ResponseStatus(HttpStatus.CREATED)
    public void addExamField(@PathVariable UUID examTypeId,
                             @Valid @RequestBody ExamTypeDto examTypeDto) {
        examTypeService.addExamField(examTypeId, examTypeDto);
    }

    @DeleteMapping("/{examTypeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExamType(@PathVariable UUID examTypeId) {
        examTypeService.deleteExamType(examTypeId);
    }
}
