package awsyoo.dev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        System.out.println(studentById1);
        System.out.println(studentById2);

        session.close();
    }
}
