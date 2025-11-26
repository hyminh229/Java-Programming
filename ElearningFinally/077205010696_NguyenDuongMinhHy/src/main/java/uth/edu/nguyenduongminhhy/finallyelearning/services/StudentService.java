package uth.edu.nguyenduongminhhy.finallyelearning.services;

import java.util.List;
import uth.edu.nguyenduongminhhy.finallyelearning.pojo.Student;
import uth.edu.nguyenduongminhhy.finallyelearning.repositories.IStudentRepository;
import uth.edu.nguyenduongminhhy.finallyelearning.repositories.StudentRepository;

public class StudentService implements IStudentService {
    private IStudentRepository iStudentRepo;
    
    public StudentService() {
        iStudentRepo = new StudentRepository();
    }
    
    @Override
    public void save(Student student) {
        iStudentRepo.addStudent(student);
    }
    
    @Override
    public List<Student> findAll() {
        // Get all students with a large page size to get all records
        return iStudentRepo.getAllStudents(0, Integer.MAX_VALUE);
    }
    
    @Override
    public void delete(int studentId) {
        Student student = iStudentRepo.getStudentById(studentId);
        if (student != null) {
            iStudentRepo.deleteStudent(student);
        }
    }
    
    @Override
    public Student findById(int studentId) {
        return iStudentRepo.getStudentById(studentId);
    }

    @Override
    public void update(Student student) {
        iStudentRepo.updateStudent(student);
    }
}
