package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamField;
import com.rutinim.exam.management.domain.ExamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamTypeRepositoryTest {

    @Autowired
    private ExamTypeRepository examTypeRepository;

    ExamType examType;
    ExamField examField;

    @BeforeEach
    void setUp() {
        examType = new ExamType();
        examType.setTypeName("a type name");
        examType.setIsPreparatoryExam(true);
        examType.setIsOnePiece(true);
        examType.setExamDuration(60);

        examField = new ExamField();
        examField.setFieldName("a field name");

        examType.addExamField(examField);

        examTypeRepository.save(examType);
    }

    @DisplayName("Test Should Exam Type is Valid")
    @Test
    void testIsExamTypeValid() {
        Optional<ExamType> _examType = examTypeRepository.findById(examType.getId());

        assertThat(_examType.get())
                .isEqualTo(examType)
                .isNotNull();

    }

}