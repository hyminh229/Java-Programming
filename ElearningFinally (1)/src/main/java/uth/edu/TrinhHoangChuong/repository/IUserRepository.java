package uth.edu.TrinhHoangChuong.repository;

import uth.edu.TrinhHoangChuong.model.User;
import java.util.List;

public interface IUserRepository {
    
    public List<User> findAll();
    
    public void save(User user);
    
    public void delete(String userId);
    
    public User findById(String userId);
    
    public void update(User user);
}

