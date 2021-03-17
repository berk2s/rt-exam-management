package com.rutinim.exam.management.service;

import com.rutinim.exam.management.domain.Lesson;
import com.rutinim.exam.management.repository.LessonRepository;
import com.rutinim.exam.management.service.impl.LessonServiceImpl;
import com.rutinim.exam.management.web.mappers.LessonMapper;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Spy
    private final LessonMapper lessonMapper = Mappers.getMapper(LessonMapper.class);

    @InjectMocks
    private LessonServiceImpl lessonService;

    Lesson lesson;
    LessonDto lessonDto;

    UUID lessonId;

    @BeforeEach
    void setUp() {
        lessonId = UUID.randomUUID();
        lesson = Lesson.builder()
                .lessonName("a lesson name")
                .id(lessonId)
                .build();

        lessonDto = lessonMapper.lessonToLessonDto(lesson);
    }

    @DisplayName("Test Should Lesson Returns Successfully")
    @Test
    void testShouldLessonReturnsSuccessfully() {
        when(lessonRepository.findById(lessonId)).thenReturn(Optional.ofNullable(lesson));

        LessonDto returnedLessonDto = lessonService.getLesson(lessonId);

        assertThat(returnedLessonDto)
                .isEqualTo(lessonDto)
                .isNotNull();

        verify(lessonRepository).findById(lessonId);
    }

    @DisplayName("Test Should Updating Lesson Successfully")
    @Test
    void testShouldUpdatingLessonSuccessfully() {
        when(lessonRepository.getOne(lessonId)).thenReturn(lesson);

        String updatedName = "a new name";
        lessonDto.setLessonName(updatedName);

        lessonService.updateLesson(lessonId, lessonDto);

        assertThat(lesson.getLessonName())
                .isEqualTo(updatedName);

        verify(lessonRepository).save(any());
    }
}