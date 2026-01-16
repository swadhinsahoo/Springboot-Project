package net.javaguides.sms.service.impl;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.event.StudentCreatedEvent;
import net.javaguides.sms.repository.StudentRepository;
import net.javaguides.sms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ApplicationEventPublisher eventPublisher;

    // ✅ Constructor Injection (Enterprise Best Practice)
    public StudentServiceImpl(StudentRepository studentRepository,
                              ApplicationEventPublisher eventPublisher) {
        this.studentRepository = studentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * ENTERPRISE FEATURE:
     * - Saves student
     * - Publishes domain event
     * - Notification handled asynchronously
     */
    @Override
    @Transactional
    public Student saveStudent(Student student) {

        // 1️⃣ Core business logic (DB transaction)
        Student savedStudent = studentRepository.save(student);

        // 2️⃣ Publish domain event (decoupled)
        eventPublisher.publishEvent(
                new StudentCreatedEvent(savedStudent)
        );

        return savedStudent;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchStudents(String keyword) {
        return studentRepository
                .findByFirstNameContainingOrLastNameContainingOrEmailContaining(
                        keyword, keyword, keyword);
    }
}
