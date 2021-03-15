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
    @DisplayName("** Test Update Exam Publisher Fields **")
    class TestUpdateExamPublisherFields {

        String updatedName = "A different publisher name";
        String updatedImage = "new-img";
        UUID updatedPublisherId = UUID.randomUUID();

        @BeforeEach
        void setUp(){
            ExamPublisher _examPublisher = examPublisherRepository.getOne(examPublisher.getId());
            _examPublisher.setPublisherName(updatedName);
            _examPublisher.setPublisherId(updatedPublisherId);
            _examPublisher.setExamImage(updatedImage);
            examPublisherRepository.save(_examPublisher);
        }

        @DisplayName("Test Exam Publisher Name Should Update Successfully")
        @Test
        void testUpdateExamPublisherNameSuccessfully() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getPublisherName())
                    .isEqualTo(updatedName)
                    .isNotNull();
        }

        @DisplayName("Test Exam Publisher Image Should Update Successfully")
        @Test
        void testUpdateExamPublisherImageSuccessfully() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getExamImage())
                    .isEqualTo(updatedImage)
                    .isNotNull();
        }

        @DisplayName("Test Exam Publisher PublisherId Should Update Successfully")
        @Test
        void testUpdateExamPublisherPublisherIdSuccessfully() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getPublisherId())
                    .isEqualTo(updatedPublisherId)
                    .isNotNull();
        }
    }

    @Nested
    @DisplayName("** Test Exam Publisher Belongs To Exam **")
    class TestExamPublisherBelongsToExam {

        Exam exam;
        UUID examID = UUID.randomUUID();

        @BeforeEach
        void setUp(){

            exam = new Exam();
            exam.setId(examID);
            exam.setExamName("A simple exam");

            examPublisher.setExam(exam);

            examPublisherRepository.save(examPublisher);

        }

        @DisplayName("Exam Publisher Should Matches Belongs To Exam")
        @Test
        void testExamPublisherShouldMatchesBelongsToExam() {
            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getExam())
                    .isEqualTo(exam)
                    .isNotNull();
        }

        @DisplayName("Update Exam Publisher's Exam")
        @Test
        void testUpdateExamPublishersExam() {
            Exam newExam = Exam.builder().id(UUID.randomUUID()).examName("different exam!").build();

            ExamPublisher _examPublisher = examPublisherRepository.getOne(examPublisher.getId());

            _examPublisher.setExam(newExam);
            examPublisherRepository.save(_examPublisher);

            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getExam())
                    .isEqualTo(newExam)
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

        @DisplayName("Test Delete Publisher Series Successfully")
        @Test
        void testDeletePublisherSeriesSuccessfully() {
            ExamPublisher _examPublisher = examPublisherRepository.getOne(examPublisher.getId());

            _examPublisher.getPublisherSeries().remove(0);

            examPublisherRepository.save(_examPublisher);

            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getPublisherSeries().contains(publisherSeries))
                    .isEqualTo(false);
        }

        @DisplayName("Test Add Publisher Series Successfully")
        @Test
        void testAddPublisherSeriesSuccessfully() {
            ExamPublisher _examPublisher = examPublisherRepository.getOne(examPublisher.getId());

            PublisherSeries publisherSeries = PublisherSeries.builder().build();

            _examPublisher.addPublisherSeries(publisherSeries);

            examPublisherRepository.save(_examPublisher);

            Optional<ExamPublisher> optionalExamPublisher = examPublisherRepository.findById(examPublisher.getId());

            assertThat(optionalExamPublisher.get().getPublisherSeries().get(1).getExamPublisher().getId())
                    .isEqualTo(examPublisher.getId());
        }

    }

}