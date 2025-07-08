package group.fooddelivery.main.entity;

import java.sql.Date;
import java.sql.Time;

import group.fooddelivery.main.utils.Global;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String OrderNumber;
    @OneToMany
    private OrderDetails orderDetails;
    private Date orderDate;
    private Time orderTime;
    private Global.orderStatus status;
}
