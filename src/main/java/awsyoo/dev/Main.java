package awsyoo.dev;

import awsyoo.dev.service.StudentService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("awsyoo.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        StudentService studentService = context.getBean(StudentService.class);

        Student student1 = new Student("Vasya", 22);
        Student student2 = new Student("Nikita", 20);
        
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
    }
}
