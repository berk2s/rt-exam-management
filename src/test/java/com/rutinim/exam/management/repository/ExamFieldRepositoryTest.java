package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.domain.Lesson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamFieldRepositoryTest {

    @Autowired
    private ExamFieldRepository examFieldRepository;

    @Autowired
    private LessonRepository lessonRepository;

    ExamField examField;
    Lesson lesson;

    @BeforeEach
    void setUp(){
        lesson = new Lesson();
        lesson.setLessonName("a lesson name");

        lessonRepository.save(lesson);

        examField = new ExamField();
        examField.setFieldName("a field name");
        examField.setNumberOfQuestions(20);
        examField.setLesson(lesson);

        examFieldRepository.save(examField);
    }

    @DisplayName("Test Should Exam Field Getting Successfully")
    @Test
    void testShouldExamFieldGettingSuccessfully() {
        Optional<ExamField> optionalExamField = examFieldRepository.findById(examField.getId());

        assertThat(optionalExamField.get())
                .isEqualTo(examField)
                .isNotNull();

    }
    @DisplayName("Test Should Updating Lesson of Exam Field Successfully")
    @Test
    void testShouldUpdatingLessonOfExamFieldSuccessfully() {
        Lesson newLesson = new Lesson();
        newLesson.setLessonName("a new lesson name");

        lessonRepository.save(lesson);

        ExamField tempExamField = examFieldRepository.getOne(examField.getId());

        tempExamField.setLesson(newLesson);

        examFieldRepository.save(tempExamField);


        Optional<ExamField> optionalExamField = examFieldRepository.findById(examField.getId());

        assertThat(optionalExamField.get().getLesson().getLessonName())
                .isEqualTo(newLesson.getLessonName())
                .isNotNull();

    }
}