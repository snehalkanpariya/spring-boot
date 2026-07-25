package com.campus.notification_service;

public record EnrollmentEvent(Long courseId, Long studentId, String studentName) { }