package uth.edu.TrinhHoangChuong.dao;

import uth.edu.TrinhHoangChuong.model.User;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    private static EntityManager em;
    private static EntityManagerFactory emf;
    
    public UserDAO(String persistenceName) {
        emf = Persistence.createEntityManagerFactory(persistenceName);
    }
    
    public void save(User user) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            users = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return users;
    }
    
    public void delete(String userId) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public User findById(String userId) {
        User user = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            user = em.find(User.class, userId);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }
    
    public void update(User user) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            User u = em.find(User.class, user.getId());
            if (u != null) {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                u.setMark(user.getMark());
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}

