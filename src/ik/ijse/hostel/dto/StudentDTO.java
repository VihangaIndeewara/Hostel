package ik.ijse.hostel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String studentId;
    private String name;
    private String address;
    private String dob;
    private String gender;
    private String contactNo;



}
