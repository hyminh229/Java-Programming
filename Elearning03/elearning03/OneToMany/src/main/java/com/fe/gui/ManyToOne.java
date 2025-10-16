package com.fe.gui;

import java.util.ArrayList;

import com.fe.pojo.Book;
import com.fe.pojo.Student;
import com.fe.service.IStudentService;
import com.fe.service.StudentService;
import jakarta.persistence.*;
import java.util.List;


public class ManyToOne {
    public static void main(String[] args) {
        String fileName = "JPAs";
        IStudentService studentService = new StudentService(fileName);
        Student student = new Student("Lam", "Nguyen", 9);
        Book book = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
        student.getBooks().add(book);
        studentService.addStudent(student);
    }
}
        

