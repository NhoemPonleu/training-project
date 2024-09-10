package com.acledabankplc.mapper;

import com.acledabankplc.dto.CourseRequest;
import com.acledabankplc.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
    Course courseRequestToCourse(CourseRequest courseRequest);

    CourseRequest courseToCourseRequest(Course course);
    Course updateCourseFromDto(CourseRequest courseUpdateRequest, @MappingTarget Course course);
}
