package ik.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String dob;
    private String gender;
    private String contactNo;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Reservation> reservation;

    public Student(String studentId, String name, String address, String dob, String gender, String contactNo) {
        this.studentId = studentId;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.contactNo = contactNo;
    }
}
