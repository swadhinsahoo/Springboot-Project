package net.javaguides.sms.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import net.javaguides.sms.event.StudentCreatedEvent;

/**
 * Listens for StudentCreatedEvent and processes it asynchronously.
 */
@Component
public class StudentNotificationListener {

    @Async
    @EventListener
    public void handleStudentCreated(StudentCreatedEvent event) {

        // Simulate slow external service (Email/SMS)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(
                "📧 Notification sent to student email: "
                        + event.getStudent().getEmail()
        );
    }
}
