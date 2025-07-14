package group.fooddelivery.main.dto;

import java.sql.Date;
import java.sql.Time;

import group.fooddelivery.main.utils.Global;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Required field!")
    private String OrderNumber;
    // private List<OrderDetailDTO> orderDetailsDTOs;
    private Date orderDate;
    private Time orderTime;
    private Global.OrderStatus status;
}
