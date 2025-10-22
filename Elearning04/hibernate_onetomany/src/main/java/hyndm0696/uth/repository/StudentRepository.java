package hyndm0696.uth.repository;

import java.util.List;
import hyndm0696.uth.dao.StudentDAO;
import hyndm0696.uth.pojo.Student;

public class StudentRepository implements IStudentRepository {
    private StudentDAO studentDAO = null;

    public StudentRepository(String fileConfig) {
        studentDAO = new StudentDAO(fileConfig);

    }
    @Override
    public void save(Student student){
    studentDAO.save(student);
    }
    @Override
    public List<Student> findAll(){
        return studentDAO.getStudent();
    }
    @Override
    public void delete(Long studentID){
        studentDAO.delete(studentID);
    }
    @Override
    public Student findByID(Long studentID){
        return studentDAO.findByID(studentID);
    }
    @Override
    public void update(Student student){
        studentDAO.update(student);
    }
}