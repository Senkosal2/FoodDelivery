package group.fooddelivery.main.entity;

import jakarta.persistence.CascadeType;
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

@Entity(name="tblDeliveryDetails")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // ensure only the delivery_details is update, not effect delivery records
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;
    private boolean isAcceptOrder;
    private boolean isPreparingOrder;
    private boolean isDeliveryOnTheWay;
    private boolean isDelivered;
}
