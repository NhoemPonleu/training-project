package com.acledabankplc;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.mapper.CourseMapper;
import com.acledabankplc.model.Course;
import com.acledabankplc.repository.CourseRepository;
import com.acledabankplc.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRegisterCourse() {
        // Arrange
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName("New Course");
        courseRequest.setDescription("Course Description");

        Course course = new Course();
        course.setId(1L); // or use a mock or a stub for the ID generation
        course.setCourseName("New Course");
        course.setDescription("Course Description");

        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseService.registerNewCourse(courseRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Course", result.getCourseName());
        assertEquals("Course Description", result.getDescription());
        verify(courseRepository, times(1)).save(any(Course.class));
    }
    @Test
    public void testUpdateCourse() {
        // Arrange
        Long courseId = 4L;
        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setCourseName("Updated Course Name");
        courseRequest.setCourseCode("UC-123");
        courseRequest.setDescription("Updated Description");

        Course existingCourse = new Course();
        existingCourse.setId(courseId);
        existingCourse.setCourseName("Old Course Name");
        existingCourse.setCourseCode("OC-123");
        existingCourse.setDescription("Old Description");

        // Mock repository and mapper behavior
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));
        when(courseMapper.courseRequestToCourse(courseRequest)).thenReturn(existingCourse);
        when(courseRepository.save(any(Course.class))).thenReturn(existingCourse);

        // Act
        Course updatedCourse = courseService.updateCourse(courseRequest, courseId);

        // Assert
        verify(courseRepository).findById(courseId);
        verify(courseMapper).courseRequestToCourse(courseRequest);
        verify(courseRepository).save(existingCourse);

        assertEquals("Updated Course Name", updatedCourse.getCourseName());
        assertEquals("UC-123", updatedCourse.getCourseCode());
        assertEquals("Updated Description", updatedCourse.getDescription());
    }
}
