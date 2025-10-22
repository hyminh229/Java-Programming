package hyndm0696.uth.service;

import java.util.List;

import hyndm0696.uth.pojo.Student;
import hyndm0696.uth.repository.IStudentRepository;
import hyndm0696.uth.repository.StudentRepository;

public class StudentService implements IStudentService {
    private IStudentRepository iStudentRepo = null;

    public StudentService(String fileName) {
        iStudentRepo = new StudentRepository(fileName);

    }

    @Override
    public void save(Student student) {
        iStudentRepo.save(student);

    }

    @Override
    public List<Student> findAll() {
        return iStudentRepo.findAll();
    }

    @Override
    public void delete(Long studentID) {
        iStudentRepo.delete(studentID);
    }

    @Override
    public Student findByID(Long studentID) {
        return iStudentRepo.findByID(studentID);
    }

    @Override
    public void update(Student student) {
        iStudentRepo.update(student);
    }
}
