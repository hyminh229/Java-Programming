package hyminh.uth.model;

import java.util.*;

public class Admin extends User {
    public Admin(String userId, String username, String password, String email, String phone) {
        super(userId, username, password, email, phone);
        this.role = "ADMIN";
    }

    @Override
    public String toString() {
        return "Admin{" + "userId='" + userId + "', username='" + username +
                "', email='" + email + "', phone='" + phone + "'}";
    }
}