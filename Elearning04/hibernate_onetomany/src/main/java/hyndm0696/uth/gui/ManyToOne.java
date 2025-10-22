package hyndm0696.uth.gui;

import hyndm0696.uth.pojo.Book;
import hyndm0696.uth.pojo.Student;
import hyndm0696.uth.service.IStudentService;
import hyndm0696.uth.service.StudentService;

public class ManyToOne {
    public static void main(String[]    args) {
     String fileName="hibernate.cfg.xml";
     IStudentService studentService = new StudentService(fileName);
     Student student = new Student("Lam","Nguyen",9);
     Book book = new Book("Java Persistence with Hibernate", "Nguyen Van A", "9781617299186");
    student.getBooks().add(book);
    studentService.save(student);

}
}
