package group.fooddelivery.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="tblOrderDetails")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int id;
    // ensure that only order_details is updated when make changes, no change to parent(Order)
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="food_id")
    private Food food;
    private int quantity;

    
    
}
