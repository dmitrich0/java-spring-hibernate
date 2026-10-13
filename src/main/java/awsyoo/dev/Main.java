package awsyoo.dev;

import awsyoo.dev.service.ProfileService;
import awsyoo.dev.service.StudentService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Main {
    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("awsyoo.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);

        Student student1 = new Student("Vasya", 22);
        Student student2 = new Student("Nikita", 20);
        
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        
        Profile profile1 = new Profile("My bio", LocalDateTime.now(), student1);
        
        profileService.saveProfile(profile1);
    }
}
