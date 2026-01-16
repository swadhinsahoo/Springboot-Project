package net.javaguides.sms.event;

import net.javaguides.sms.entity.Student;

/**
 * Domain Event
 * Represents the business fact that a student was created.
 */
public class StudentCreatedEvent {

    private final Student student;

    public StudentCreatedEvent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
