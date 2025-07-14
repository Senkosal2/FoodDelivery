package group.fooddelivery.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="tblTopping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private double price;
    // all operation, remove(delete), merge(update), detach(not relate), persist(not effect related), refresh(refresh updated)
    // @ManyToMany(mappedBy="toppings", cascade=CascadeType.ALL) 
    // private List<Food> foods;
}
