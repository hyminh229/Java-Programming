package com.fe.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import com.fe.pojo.Student;

public class StudentDAO {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public StudentDAO(String persistenceName){
        emf = Persistence.createEntityManagerFactory(persistenceName);
    }

    public void save(Student student){
        try {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
    } catch (Exception ex) {
        em.getTransaction().rollback();
        System.out.println("Error: " + ex.getMessage());
    } finally {
        em.close();
    }
    }
    
    public List<Student> getStudents(){
        List<Student> students = null;
        try{
            em = emf.createEntityManager();
            em.getTransaction().begin();
            students = em.createQuery("from student").getResultList();
        } catch (Exception ex) {

            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
        return students;
    }

    // đổi long thành int
    public void delete(long studentID){
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student s = em.find(Student.class, studentID);
            em.remove( s );
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    // đổi long thành int
    public Student findByID(long studentID){
        Student student = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            student = em.find(Student.class, studentID);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
        return student;
    }

    public void update(Student student){
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student s = em.find(Student.class, student.getId());
            if (s != null) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setMarks(student.getMarks());
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
