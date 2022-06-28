package ik.ijse.hostel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    private String address;
    private String dob;
    private String gender;
    private String contactNo;
}
