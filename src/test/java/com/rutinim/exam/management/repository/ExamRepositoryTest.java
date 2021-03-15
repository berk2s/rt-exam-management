package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.domain.ExamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamRepositoryTest {

    @Autowired
    private ExamRepository examRepository;

    Exam exam;

    @BeforeEach
    void setUp(){
        exam = new Exam();
        exam.setExamName("A simple exam");

        examRepository.save(exam);
    }

    @DisplayName("Should Exam Returns Successfully")
    @Test
    void shouldExamReturnsSuccessfully() {
        Optional<Exam> examOptional = examRepository.findById(exam.getId());

        assertThat(examOptional.get())
                .isEqualTo(exam)
                .isNotNull();
    }

    @DisplayName("** Test Exam Type Belongs To Exam Behaviour **")
    @Nested
    class TestExamTypeBelongsToExamBehaviour {

        ExamType examType;

        @BeforeEach
        void setUp(){
            examType = new ExamType();
            examType.setIsPreparatoryExam(true);
            examType.setExamDuration(70);
            examType.setTypeName("A Simple Exam Type");

            exam.setExamType(examType);

            examRepository.save(exam);
        }

        @DisplayName("Should Exam Type Saved Successfully")
        @Test
        void testShouldExamTypeSavedSuccessfully() {
            Optional<Exam> optionalExam = examRepository.findById(exam.getId());

            assertThat(optionalExam.get().getExamType())
                    .isEqualTo(examType)
                    .isNotNull();

        }

    }

}