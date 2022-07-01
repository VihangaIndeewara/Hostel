package ik.ijse.hostel.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    private String resId;
    private String type;
    private Double keyMoney;
    private Double payingAmount;
    private String dateFrom;
    private String dateTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "roomTypeId")
    private Room room;


    private  String sId;
    private String rId;

    public Reservation(String resId, String type, Double keyMoney, Double payingAmount, String dateFrom, String dateTo, Student student, Room room) {
        this.resId = resId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.payingAmount = payingAmount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.student = student;
        this.room = room;
    }


    public Reservation(String resId, String type, Double keyMoney, Double payingAmount, String dateFrom, String dateTo, String studentId, String roomTypeId) {
        this.resId = resId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.payingAmount = payingAmount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.sId = studentId;
        this.rId = roomTypeId;
    }
}
