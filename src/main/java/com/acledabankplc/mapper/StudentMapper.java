package com.acledabankplc.mapper;

import com.acledabankplc.dto.StudentDTO;
import com.acledabankplc.model.Course;
import com.acledabankplc.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source ="course.id", target = "courseId")
    StudentDTO studentToStudentDTO(Student student);

    @Mapping(source = "courseId", target = "course.id")
    Student studentDTOToStudent(StudentDTO studentDTO);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "course", source = "courseId", qualifiedByName = "mapCourse")
    Student updateStudentFromDTO(StudentDTO studentDTO, @MappingTarget Student student);

    @Named("mapCourse")
    default Course mapCourse(Long courseId) {
        if (courseId == null) {
            return null;
        }
        Course course = new Course();
        course.setId(courseId);
        return course;
    }

}
