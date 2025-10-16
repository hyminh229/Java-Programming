package com.fe.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "first_name", nullable = false, unique = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "marks")
    private int marks;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "STUDENTS_BOOKS",
        joinColumns = @JoinColumn(name = "student_id"), 
        inverseJoinColumns = @JoinColumn(name = "book_id"))
        private Set<Book> books = new HashSet<Book>();
    }   
    // checkmate
    public Student(String firstName, String lastName, int marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getMarks() {
        return marks;
    }
    public void setMarks(int marks) {
        this.marks = marks;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", marks=" + marks + "]";
    }
    public Object getBooks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBooks'");
    }
