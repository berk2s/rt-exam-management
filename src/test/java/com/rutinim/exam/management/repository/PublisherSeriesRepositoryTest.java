package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamPublisher;
import com.rutinim.exam.management.domain.PublisherSeries;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PublisherSeriesRepositoryTest {

    @Autowired
    private ExamPublisherRepository examPublisherRepository;

    @Autowired
    private PublisherSeriesRepository publisherSeriesRepository;

    ExamPublisher examPublisher;
    PublisherSeries publisherSeries;

    @BeforeEach
    void setUp() {
        publisherSeries = new PublisherSeries();
        publisherSeries.setSequenceName("a sequence name");
        publisherSeries.setNumberOfSequence(1);

        examPublisher = new ExamPublisher();
        examPublisher.setPublisherName("a simple exam publisher");
        examPublisher.setNumberOfSeries(1);
        examPublisher.addPublisherSeries(publisherSeries);

        examPublisherRepository.save(examPublisher);
    }

    @DisplayName("Test Publisher Series Update")
    @Test
    void testPublisherSeriesUpdate() {
        PublisherSeries savedPublisherSeries = publisherSeriesRepository.getOne(publisherSeries.getId());

        savedPublisherSeries.setSequenceName("changed sequence name");

        publisherSeriesRepository.save(savedPublisherSeries);

        Optional<ExamPublisher> _examPublisher = examPublisherRepository.findById(examPublisher.getId());

        assertThat(_examPublisher.get().getPublisherSeries().get(0).getSequenceName())
                .isEqualTo(savedPublisherSeries.getSequenceName())
                .isNotNull();

    }

    @DisplayName("Test Delete Publisher Series Successfully")
    @Test
    void testDeletePublisherSeriesSuccessfully() {
        PublisherSeries savedPublisherSeries = publisherSeriesRepository.getOne(publisherSeries.getId());

        ExamPublisher _examPublisher = examPublisherRepository.getOne(examPublisher.getId());
        _examPublisher.getPublisherSeries().remove(savedPublisherSeries);

        examPublisherRepository.save(_examPublisher);

        assertThat(_examPublisher.getPublisherSeries().size())
                .isEqualTo(0)
                .isNotNull();
    }

}