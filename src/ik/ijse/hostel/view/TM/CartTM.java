package ik.ijse.hostel.view.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTM {
    String stuId;
    String roomTypeId;
    String type;
    double keyMoney;
    double payingAmount;
    String dateFrom;
    String dateTo;
}
