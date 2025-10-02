package hyminh.uth.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

public class Attendance implements Serializable {
    private String attendanceId;
    private String memberId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private boolean present;

    public Attendance(String attendanceId, String memberId, LocalDate date) {
        this.attendanceId = attendanceId;
        this.memberId = memberId;
        this.date = date;
        this.present = false;
    }

    public void markPresent(LocalTime checkInTime) {
        this.present = true;
        this.checkInTime = checkInTime;
    }

    public void markCheckOut(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getAttendanceId() { return attendanceId; }
    public String getMemberId() { return memberId; }
    public LocalDate getDate() { return date; }
    public boolean isPresent() { return present; }
    public LocalTime getCheckInTime() { return checkInTime; }
    public LocalTime getCheckOutTime() { return checkOutTime; }

    @Override
    public String toString() {
        return "Attendance{" + "id='" + attendanceId + "', memberId='" + memberId +
                "', date=" + date + ", present=" + present + "}";
    }
}