package com.rutinim.exam.management.web.controllers;

import com.rutinim.exam.management.service.ExamService;
import com.rutinim.exam.management.web.model.ExamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDto>> listExams() {
        return new ResponseEntity<>(examService.listExams(), HttpStatus.OK);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<ExamDto> getExam(@PathVariable UUID examId) {
        return new ResponseEntity<>(examService.getExam(examId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExam(@Valid @RequestBody ExamDto examDto) {
        examService.saveExam(examDto);
    }

    @PutMapping("/{examId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExam(@PathVariable UUID examId, @Valid @RequestBody ExamDto examDto){
        examService.updateExam(examId, examDto);
    }

    @DeleteMapping("/{examId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExam(@PathVariable UUID examId) {
        examService.deleteExam(examId);
    }

}
