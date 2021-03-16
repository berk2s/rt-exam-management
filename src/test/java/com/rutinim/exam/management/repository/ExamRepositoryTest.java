package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.Exam;
import com.rutinim.exam.management.domain.ExamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamRepositoryTest {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamTypeRepository examTypeRepository;

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

            exam.addExamType(examType);

            examRepository.save(exam);
        }

        @DisplayName("Should Exam Type Saved Successfully")
        @Test
        void testShouldExamTypeSavedSuccessfully() {
            Optional<Exam> optionalExam = examRepository.findById(exam.getId());

            assertThat(optionalExam.get().getExamTypes().get(0).getTypeName())
                    .isEqualTo(examType.getTypeName())
                    .isNotNull();

        }

        @DisplayName("Should Exam Type Saved In Table")
        @Test
        void testShouldExamTypeSavedInTableSuccessfully() {
            List<ExamType> optionalExamType = examTypeRepository.findAll();

            assertThat(optionalExamType.size())
                    .isEqualTo(1)
                    .isNotNull();

        }

        @DisplayName("Should Exam Type Deleting Safely")
        @Test
        void testShouldDeletingExamTypeSafely() {
            Exam tempExam = examRepository.getOne(exam.getId());
            tempExam.getExamTypes().get(0).setExam(null);
            tempExam.getExamTypes().remove(0);

            examRepository.save(tempExam);

            List<ExamType> optionalExamType = examTypeRepository.findAll();

            assertThat(optionalExamType.get(0).getExam())
                    .isEqualTo(null);
        }

        @DisplayName("Should Updating Exam Type's Exam Successfully")
        @Test
        void testShouldUpdatingExamTypesExamSuccessfully() {
            ExamType tempExamType = examTypeRepository.getOne(exam.getExamTypes().get(0).getId());

            tempExamType.getExam().getExamTypes().remove(tempExamType);

            Exam tempExam = new Exam();
            tempExam.setExamName("a different exam");
            tempExam.addExamType(tempExamType);

            examRepository.save(tempExam);

            tempExamType.setTypeName("changed name");
            tempExamType.setExam(tempExam);

            examTypeRepository.save(tempExamType);

            Optional<ExamType> optionalExamType = examTypeRepository.findById(tempExamType.getId());

            assertThat(optionalExamType.get().getExam().getExamName())
                    .isEqualTo(tempExam.getExamName())
                    .isNotNull();

            Optional<Exam> optionalExam = examRepository.findById(exam.getId());

            assertThat(optionalExam.get().getExamTypes().size())
                    .isEqualTo(0)
                    .isNotNull();

        }

    }


}