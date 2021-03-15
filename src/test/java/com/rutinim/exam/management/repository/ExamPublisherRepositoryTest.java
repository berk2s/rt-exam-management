package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExamPublisherRepositoryTest {

    @Autowired
    private ExamPublisherRepository examPublisherRepository;

    ExamPublisher examPublisher;

    @BeforeEach
    void setUp(){

        examPublisher = new ExamPublisher();
        examPublisher.setPublisherId(UUID.randomUUID());
        examPublisher.setExamImage("img");
        examPublisher.setPublisherName("Rutinim Yayinlari");

        examPublisherRepository.save(examPublisher);

    }

    @DisplayName("Test Exam Publisher Should Successfully saved")
    @Test
    void testExamPublisherSuccessfullySaved() {
        Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

        assertThat(optionalExamPublisher.get())
                .isEqualTo(examPublisher)
                .isNotNull();
    }


    @Nested
    @DisplayName("** Test Exam Publsihers Belongs To Exam **")
    class TestExamPublisherBelongsToExam {

        Exam exam;

        @BeforeEach
        void setUp(){

            exam = new Exam();
            exam.setId(UUID.randomUUID());
            exam.setExamName("A simple exam");

            examPublisher.setExam(exam);

            examPublisherRepository.save(examPublisher);

        }

        @DisplayName("Exam Publisher Should Matches Belongs To Exam")
        @Test
        void examPublisherShouldMatchesBelongsToExam() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getExam())
                    .isEqualTo(exam)
                    .isNotNull();

        }

    }

    @Nested
    @DisplayName("** Test Exam Publisher Series Belongs To Exam Publisher **")
    class TestPublisherSeriesBelongsToPublisher {

        PublisherSeries publisherSeries;
        List<PublisherSeries> publisherSeriesList;

        @BeforeEach
        void setUp() {

            publisherSeries = new PublisherSeries();
            publisherSeries.setSequenceName("First Exam");
            publisherSeries.setNumberOfSequence(1);

            publisherSeriesList = new ArrayList<PublisherSeries>();
            publisherSeriesList.add(publisherSeries);

            examPublisher.setPublisherSeries(publisherSeriesList);

            examPublisherRepository.save(examPublisher);

        }

        @DisplayName("Test Publisher Series Matches Exam Publsiher")
        @Test
        void testPublisherSeriesMatchesPublisher() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getPublisherSeries())
                    .isEqualTo(publisherSeriesList);

        }

    }

}