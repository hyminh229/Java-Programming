package hyndm0696.uth.service;

import java.util.List;

import hyndm0696.uth.pojo.Student;

public interface IStudentService {
    public List<Student> findAll();

    public void save(Student student);

    public void delete(Long studentID);

    public Student findByID(Long studentID);

    public void update(Student student);
    
}
