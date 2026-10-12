package awsyoo.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    static void main() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("awsyoo.dev");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        Session session = sessionFactory.openSession();

        Student student1 = new Student("Vasya", 22);
        Student student2 = new Student("Nikita", 20);

        session.beginTransaction();
        session.persist(student1);
        session.persist(student2);
        session.getTransaction().commit();

        Student studentById1 = session.get(Student.class, 1L);

        System.out.println(studentById1);

        Student studentById2 = session.createQuery(
                        "select s from Student s where s.id = :id", Student.class)
                .setParameter("id", 2)
                .getSingleResult();
        
        session.beginTransaction();
        Student studentForUpdate = session.get(Student.class, 1L);
        studentForUpdate.setAge(30);
        studentForUpdate.setName("Dima");
        session.getTransaction().commit();
        
        session.beginTransaction();
        
//        Student studentToDelete = session.get(Student.class, 2L);
//        session.remove(studentToDelete);

//        session.createQuery(
//                "delete from Student s where s.id = 2"
//        ).executeUpdate();
        
//        session.createNativeQuery("delete from students s where s.id = 2").executeUpdate();

        List<Student> allStudents = session.createQuery("select s from Student s", Student.class).list();

        Student studentByName = session.createQuery(
                        "select s from Student s where s.name = :name", Student.class)
                .setParameter("name", "Dima")
                .getSingleResult();
        
        session.getTransaction().commit();

        System.out.println(studentById1);
        System.out.println(studentById2);

        session.close();
    }
}
