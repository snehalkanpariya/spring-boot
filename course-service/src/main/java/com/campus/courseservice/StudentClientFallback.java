package com.campus.courseservice;

import org.springframework.stereotype.Component;

// Used automatically when student-service is unreachable
@Component
public class StudentClientFallback implements StudentClient {

    @Override
    public StudentDto getStudent(Long id) {
        return new StudentDto(id, "Unknown (student-service is down)", "n/a");
    }
}
