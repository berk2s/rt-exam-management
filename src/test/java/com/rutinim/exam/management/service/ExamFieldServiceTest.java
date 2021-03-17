package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.domain.Lesson;
import com.rutinim.exam.management.repository.ExamFieldRepository;
import com.rutinim.exam.management.repository.LessonRepository;
import com.rutinim.exam.management.service.impl.ExamFieldServiceImpl;
import com.rutinim.exam.management.web.mappers.ExamFieldMapper;
import com.rutinim.exam.management.web.model.ExamFieldDto;
import com.rutinim.exam.management.web.model.LessonDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamFieldServiceTest {

    @Mock
    private ExamFieldRepository examFieldRepository;

    @Mock
    private LessonRepository lessonRepository;

    @Spy
    private ExamFieldMapper examFieldMapper = Mappers.getMapper(ExamFieldMapper.class);

    @InjectMocks
    private ExamFieldServiceImpl examFieldService;

    ExamField examField;
    ExamFieldDto examFieldDto;

    LessonDto lessonDto;
    Lesson lesson;

    UUID examFieldId;
    UUID lessonId;

    @BeforeEach
    void setUp(){
        examFieldId = UUID.randomUUID();
        lessonId = UUID.randomUUID();

        lesson = new Lesson();
        lesson.setId(lessonId);
        lesson.setLessonName("a lesson name");

        examField = new ExamField();
        examField.setFieldName("a simple exam field anme");
        examField.setNumberOfQuestions(20);
        examField.setLesson(lesson);
        examField.setId(examFieldId);

        examFieldDto = examFieldMapper.examFieldToExamFieldDto(examField);
    }

    @DisplayName("Test Should Exam Field Returns Successfully")
    @Test
    void testShouldExamFieldReturnSuccessfully() {
        when(examFieldRepository.findById(examFieldId)).thenReturn(Optional.ofNullable(examField));

        ExamFieldDto returnedExamFieldDto = examFieldService.getExamField(examFieldId);

        assertThat(returnedExamFieldDto)
                .isEqualTo(examFieldDto)
                .isNotNull();

        verify(examFieldRepository).findById(examFieldId);
    }

    @DisplayName("Test Should Update Exam Field Successfully")
    @Test
    void testShouldUpdateExamFieldSuccessfully() {
        String newFieldName = "an updated field name";
        examFieldDto.setFieldName(newFieldName);
        when(examFieldRepository.getOne(examFieldId)).thenReturn(examField);

        examFieldDto.setIsChanged(false);

        examFieldService.updateExamField(examFieldId, examFieldDto);

        assertThat(examField.getFieldName())
                .isEqualTo(newFieldName)
                .isNotNull();

        verify(examFieldRepository).getOne(any());
        verify(examFieldRepository).save(any());
    }


    @DisplayName("Test Should Update(Contains Lesson) Exam Field Successfully")
    @Test
    void testShouldUpdateExamFieldWithLessonSuccessfully() {
        UUID newLessonId = UUID.randomUUID();
        String updatedLessonName = "a new lesson name";

        Lesson newLesson = new Lesson();
        newLesson.setId(newLessonId);
        newLesson.setLessonName(updatedLessonName);

        when(examFieldRepository.getOne(examFieldId)).thenReturn(examField);
        when(lessonRepository.getOne(newLessonId)).thenReturn(newLesson);

        examFieldDto.setLessonId(newLessonId);
        examFieldDto.setIsChanged(true);

        examFieldService.updateExamField(examFieldId, examFieldDto);

        assertThat(examField.getLesson().getLessonName())
                .isEqualTo(updatedLessonName)
                .isNotNull();

        assertThat(examField.getLesson().getId())
                .isEqualTo(newLessonId)
                .isNotNull();

        verify(lessonRepository).getOne(any());
        verify(examFieldRepository).getOne(any());
        verify(examFieldRepository).save(any());

    }

}