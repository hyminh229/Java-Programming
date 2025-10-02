package hyminh.uth.model;

import java.util.*;

public class Trainer extends User {
    private String specialization;
    private List<String> assignedMemberIds;

    public Trainer(String userId, String username, String password, String email,
                   String phone, String specialization) {
        super(userId, username, password, email, phone);
        this.role = "TRAINER";
        this.specialization = specialization;
        this.assignedMemberIds = new ArrayList<>();
    }

    public String getSpecialization() { return specialization; }
    public List<String> getAssignedMemberIds() { return assignedMemberIds; }

    public void assignMember(String memberId) {
        if (!assignedMemberIds.contains(memberId)) {
            assignedMemberIds.add(memberId);
        }
    }

    @Override
    public String toString() {
        return "Trainer{" + "userId='" + userId + "', username='" + username +
                "', specialization='" + specialization + "', assignedMembers=" +
                assignedMemberIds.size() + "}";
    }
}