package uth.edu.nguyenduongminhhy.finallyelearning.services;

import java.util.List;
import uth.edu.nguyenduongminhhy.finallyelearning.pojo.Student;

public interface IStudentService{
    public List<Student> findAll();
    public void save (Student student);
    public void delete(int studentId);
    public Student findById(int studentId);
    public void update(Student student);
}