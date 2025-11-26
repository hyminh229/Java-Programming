package uth.edu.TrinhHoangChuong.service;

import uth.edu.TrinhHoangChuong.model.User;
import uth.edu.TrinhHoangChuong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    @Override
    public void save(User user) {
        userRepository.save(user);
    }
    
    @Override
    public void delete(String userId) {
        userRepository.delete(userId);
    }
    
    @Override
    public User findById(String userId) {
        return userRepository.findById(userId);
    }
    
    @Override
    public void update(User user) {
        userRepository.update(user);
    }
}

