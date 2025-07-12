package group.fooddelivery.main.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import group.fooddelivery.main.utils.Global;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private String OrderNumber;
    private List<OrderDetailsDTO> orderDetailsDTOs;
    private Date orderDate;
    private Time orderTime;
    private Global.orderStatus status;
}
