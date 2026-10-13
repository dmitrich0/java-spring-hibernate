package awsyoo.dev.service;

import awsyoo.dev.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    
    private final SessionFactory sessionFactory;

    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Student saveStudent(Student student) {
        Session session = sessionFactory.openSession();
        
        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();
        
        session.close();
        
        return student;
    }
    
    
    public void removeStudent(Long id) {
        Session session = sessionFactory.openSession();
        
        Student student = session.get(Student.class, id);
        session.remove(student);
        
        session.close();
    }
    
    public Student getById(Long id) {
        Session session = sessionFactory.openSession();

        Student student = session.get(Student.class, id);

        session.close();
        
        return student;
    }
    
    public List<Student> findAll() {
        Session session = sessionFactory.openSession();

        List<Student> students = session.createQuery("select s from Student s", Student.class).list();

        session.close();

        return students;
    }
    
    public Student update(Student student) {
        Session session = sessionFactory.openSession();
        
        student = session.merge(student);
        session.getTransaction().commit();

        session.close();
        
        return student;
    }
    
}
