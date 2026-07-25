package com.campus.courseservice;

public record EnrollmentEvent(Long courseId, Long studentId, String studentName) { }