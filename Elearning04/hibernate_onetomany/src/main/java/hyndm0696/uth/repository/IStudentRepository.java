package hyndm0696.uth.repository;

import java.util.List;
import hyndm0696.uth.pojo.Student;

public interface IStudentRepository {
    public List<Student> findAll();
    public void save(Student student);
    public void delete(Long studentID);
    public Student findByID(Long studentID);
    public void update(Student student);
    
}
