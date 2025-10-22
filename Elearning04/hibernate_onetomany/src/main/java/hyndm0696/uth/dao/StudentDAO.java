package hyndm0696.uth.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; // <-- dùng Transaction của Hibernate
import org.hibernate.cfg.Configuration;

import hyndm0696.uth.pojo.Student;

public class StudentDAO {

    private SessionFactory sessionFactory = null;
    private Configuration cf = null;

    public StudentDAO(String persistenceName) {
        cf = new Configuration();
        cf = cf.configure(persistenceName);
        sessionFactory = cf.buildSessionFactory();
    }

    public void save(Student student) {

        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(student);
            t.commit();
            System.out.println("successfully saved");

        } catch (Exception ex) {
            t.rollback();
            System.out.println("Error" + ex.getMessage());
        }   finally {
            sessionFactory.close();
            session.close();
        }
    }

    public List<Student> getStudent() 
    {
        Session session = sessionFactory.openSession();
        try {
            List<Student> students = session.createQuery("from Student", Student.class).getResultList();
            return students;
        } catch (RuntimeException e){
            throw e;
        } finally {
            session.close();    
        }
 

    }

    public void delete(Long studentID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Student student = (Student) session.get(Student.class, studentID);
            session.delete(student);
            tx.commit();
        } catch (RuntimeException e){
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Student findByID(Long studentID) {
        Session session = sessionFactory.openSession();
        try {
            return (Student) session.get(Student.class, studentID);

        } catch (RuntimeException e){
            throw e;
        } finally {
            session.close();    
        }
    }

    public void update(Student student) {
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(student);
            t.commit();
            System.out.println("update saved");

        } catch (Exception ex) {
            t.rollback();
            System.out.println("Error" + ex.getMessage());
        }   finally {
            sessionFactory.close();
            session.close();
        }

}
}