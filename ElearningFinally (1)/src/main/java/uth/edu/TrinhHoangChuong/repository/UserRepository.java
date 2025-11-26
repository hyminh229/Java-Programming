package uth.edu.TrinhHoangChuong.repository;

import uth.edu.TrinhHoangChuong.dao.UserDAO;
import uth.edu.TrinhHoangChuong.model.User;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {
    
    private UserDAO userDAO;
    
    public UserRepository() {
        try {
            userDAO = new UserDAO("JPAs");
        } catch (Exception e) {
            System.err.println("Error initializing UserDAO: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void ensureUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO("JPAs");
        }
    }
    
    @Override
    public List<User> findAll() {
        ensureUserDAO();
        return userDAO.getUsers();
    }
    
    @Override
    public void save(User user) {
        ensureUserDAO();
        userDAO.save(user);
    }
    
    @Override
    public void delete(String userId) {
        ensureUserDAO();
        userDAO.delete(userId);
    }
    
    @Override
    public User findById(String userId) {
        ensureUserDAO();
        return userDAO.findById(userId);
    }
    
    @Override
    public void update(User user) {
        ensureUserDAO();
        userDAO.update(user);
    }
}

