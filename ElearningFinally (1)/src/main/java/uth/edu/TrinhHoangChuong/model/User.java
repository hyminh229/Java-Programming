package uth.edu.TrinhHoangChuong.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "mark")
    private String mark;
    
    public User() {
    }
    
    public User(String id, String firstName, String lastName, String mark) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mark = mark;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
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
    
    public String getMark() {
        return mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }
}

