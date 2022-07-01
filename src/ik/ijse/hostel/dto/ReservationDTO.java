package ik.ijse.hostel.dto;

import ik.ijse.hostel.entity.Room;
import ik.ijse.hostel.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    String resId;
    String type;
    Double keyMoney;
    Double payingAmount;
    String dateFrom;
    String dateTo;
    Student studentId;
    Room roomId;

    String sId;
    String rId;

    public ReservationDTO(String resId, String type, Double keyMoney, Double payingAmount, String dateFrom, String dateTo, Student studentId, Room roomId) {
        this.resId = resId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.payingAmount = payingAmount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.studentId = studentId;
        this.roomId = roomId;
    }

    public ReservationDTO(String resId, String type, Double keyMoney, Double payingAmount, String dateFrom, String dateTo, String sId, String rId) {
        this.resId = resId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.payingAmount = payingAmount;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.sId = sId;
        this.rId = rId;
    }
}
