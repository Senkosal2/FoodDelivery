package group.fooddelivery.main.entity;

import java.sql.Date;
import java.sql.Time;

import group.fooddelivery.main.utils.Global;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="tblOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String OrderNumber;
    // @OneToMany(mappedBy="order") // map by field name
    // private List<OrderDetail> orderDetails;
    private Date orderDate;
    private Time orderTime;

    @Enumerated(EnumType.STRING)
    private Global.OrderStatus status;
}
