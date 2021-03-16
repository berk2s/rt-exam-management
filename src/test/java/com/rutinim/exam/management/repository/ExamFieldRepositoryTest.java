package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamField;
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

    ExamField examField;

    @BeforeEach
    void setUp(){
        examField = new ExamField();
        examField.setFieldName("a field name");
        examField.setNumberOfQuestions(20);

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
}