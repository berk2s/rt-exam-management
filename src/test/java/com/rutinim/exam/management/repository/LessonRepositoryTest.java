package com.rutinim.exam.management.repository;

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
class LessonRepositoryTest {

    @Autowired
    private LessonRepository lessonRepository;

    Lesson lesson;

    @BeforeEach
    void setUp() {
        lesson = new Lesson();
        lesson.setLessonName("A lesson name");

        lessonRepository.save(lesson);
    }

    @DisplayName("Test Should Lesson Getting Successfully")
    @Test
    void testLessonGettingSuccessfully() {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lesson.getId());

        assertThat(optionalLesson.get())
                .isEqualTo(lesson)
                .isNotNull();

    }

    @DisplayName("Test Should Lesson Updating Successfully")
    @Test
    void testLessonUpdatingSuccessfully() {
        Lesson tempLesson = lessonRepository.getOne(lesson.getId());

        tempLesson.setLessonName("a different lesson name");

        lessonRepository.save(tempLesson);

        Optional<Lesson> optionalLesson = lessonRepository.findById(lesson.getId());

        assertThat(optionalLesson.get().getLessonName())
                .isEqualTo(tempLesson.getLessonName())
                .isNotNull();
    }


}