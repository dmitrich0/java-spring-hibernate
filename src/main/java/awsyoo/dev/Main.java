package awsyoo.dev;

import awsyoo.dev.service.GroupService;
import awsyoo.dev.service.ProfileService;
import awsyoo.dev.service.StudentService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("awsyoo.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);

        Group group1 = groupService.saveGroup("1", 2024L);
        Group group2 = groupService.saveGroup("2", 2025L);
        Group group3 = groupService.saveGroup("3", 2026L);

        Student student1 = new Student("Vasya", 22, group1);
        Student student2 = new Student("Nikita", 20, group1);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        
        groupService.getAll();
    }
}
