package com.campus.courseservice;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final EnrollmentPublisher publisher;
    private final List<Course> courses = List.of(
            new Course(1L, "Microservices with Spring", 30),
            new Course(2L, "Cloud Computing", 40),
            new Course(3L, "Data Structures", 60)
    );

    private final StudentClient studentClient;   // our connection!

    public CourseController(StudentClient studentClient, EnrollmentPublisher publisher) {
    this.studentClient = studentClient;
    this.publisher = publisher;
}

    // GET http://localhost:8082/courses
    @GetMapping
    public List<Course> all() {
        return courses;
    }

    // POST http://localhost:8082/courses/1/enroll/2
    @PostMapping("/{courseId}/enroll/{studentId}")
    public Map<String, Object> enroll(@PathVariable Long courseId,
                                      @PathVariable Long studentId) {

        Course course = courses.stream()
                .filter(c -> c.id().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        // ===== THE INTER-SERVICE CALL =====
        // Looks like a normal method call, but travels over the network
        // to student-service (found via Eureka, guarded by circuit breaker).
        StudentDto student = studentClient.getStudent(studentId);
        publisher.publish(new EnrollmentEvent(courseId, studentId, student.name()));
        return Map.of(
                "message", "Enrolment successful!",
                "course", course.title(),
                "student", student.name(),
                "email", student.email()
        );
    }
}
