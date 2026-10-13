package awsyoo.dev.service;

import awsyoo.dev.Student;
import awsyoo.dev.TransactionHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionHelper transactionHelper;

    public StudentService(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public Student saveStudent(Student student) {
        return this.transactionHelper.executeInTransaction(session -> {
            session.persist(student);

            return student;
        });
    }


    public void removeStudent(Long id) {
        this.transactionHelper.executeInTransaction(session -> {
            Student student = session.get(Student.class, id);
            session.remove(student);
        });
    }

    public Student getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Student.class, id);
        }
    }

    public List<Student> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select s from Student s", Student.class)
                    .list();
        }
    }
    
    public Student update(Student student) {
        return this.transactionHelper.executeInTransaction(session -> {
            return session.merge(student);
        });
    }

}
