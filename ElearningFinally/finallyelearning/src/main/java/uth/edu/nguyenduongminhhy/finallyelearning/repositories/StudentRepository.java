package uth.edu.nguyenduongminhhy.finallyelearning.repositories;

import java.util.List;

import uth.edu.nguyenduongminhhy.finallyelearning.dao.StudentDAO;
import uth.edu.nguyenduongminhhy.finallyelearning.pojo.Student;

public class StudentRepository 
implements IStudentRepository{
    private StudentDAO studentDAO = null;
    public StudentRepository(){
        studentDAO = new StudentDAO("Hibernate.cfg.xml");
    }
    @Override
    public void addStudent(Student student) {
        studentDAO.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        // TODO Auto-generated method stub
        studentDAO.updateStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDAO.deleteStudent(student);
    }

    @Override
    public Student getStudentById(long id) {
        return studentDAO.getStudentById(id);
    }

    @Override
    public List<Student> getAllStudents(int page, int pageSize) {
        return studentDAO.getAllStudents(page, pageSize);
    }

}
