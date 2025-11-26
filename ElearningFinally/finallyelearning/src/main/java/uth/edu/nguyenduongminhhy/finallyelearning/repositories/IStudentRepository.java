package uth.edu.nguyenduongminhhy.finallyelearning.repositories;

import java.util.List;

import uth.edu.nguyenduongminhhy.finallyelearning.pojo.Student;

public interface IStudentRepository {
    public void addStudent(Student student);
    public void updateStudent(Student student);
    public void deleteStudent(Student student);
    public Student getStudentById(long id);
    public List<Student> getAllStudents(int page, int pageSize);

}
