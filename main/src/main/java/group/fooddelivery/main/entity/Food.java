package group.fooddelivery.main.entity;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_food")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private double price;

    private String description;
    private long likes;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name="food_category",
        joinColumns=@JoinColumn(name="food_id"),
        inverseJoinColumns=@JoinColumn(name="category_id")
    )
    @NotNull
    private List<Category> categories;

    private String imageUrl;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
        name="food_topping",
        joinColumns=@JoinColumn(name="food_id"),
        inverseJoinColumns=@JoinColumn(name="topping_id")
    )
    @NotNull
    private List<Topping> toppings;
}
