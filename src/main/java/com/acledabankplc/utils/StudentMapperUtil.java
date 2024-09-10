package com.acledabankplc.utils;

import com.acledabankplc.model.Student;
import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Course;
import com.acledabankplc.service.CourseService;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
@RequiredArgsConstructor
public class StudentMapperUtil {
    
    private static final CourseService courseService = null;

    // Convert Student entity to StudentDTO
    public static StudentDTO toDTO(Student student) {
        if (student == null) {
            return null;
        }

        StudentDTO studentDTO = new StudentDTO();
       // studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
      //  studentDTO.setDob(student.getDob() != null ? new java.sql.Date(student.getDob().getTime()).toLocalDate() : null);
        studentDTO.setEmail(student.getEmail());
       // studentDTO.setRegisterDate(student.getRegisterDate());
        studentDTO.setCourseId(student.getCourse() != null ? student.getCourse().getId() : null);
        return studentDTO;
    }

    // Convert StudentDTO to Student entity
    public static Student toEntity(StudentDTO studentDTO, Course course) {
        if (studentDTO == null) {
            return null;
        }

        Student student = new Student();
       // student.setId(studentDTO.get());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
      //  student.setDob(studentDTO.getDob() != null ? java.sql.Date.valueOf(studentDTO.getDob()) : null);
        student.setEmail(studentDTO.getEmail());
       // student.setRegisterDate(studentDTO.getRegisterDate());
        student.setCourse(course); // Assuming course is set separately
        return student;
    }

    // Utility method to find course by ID (this should be implemented in your service or repository)
    public static Course findCourseById(Long courseId) {
        // Implement this method to fetch Course from the database or repository
        // Example:
        // return courseRepository.findById(courseId).orElse(null);
        return courseService.retrieveCourseById(courseId); // Replace with actual implementation
    }
}
