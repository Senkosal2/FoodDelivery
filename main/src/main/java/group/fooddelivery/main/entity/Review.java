package group.fooddelivery.main.entity;

import java.sql.Date;
import java.sql.Time;

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

@Entity(name="tblReviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date reviewDate;
    private Time reviewTime;
    private String comments;
    private float rating;
    // persist = when remove the user from here, ensure that the user is not update or remove as well, only review is changed
    @ManyToOne(cascade=CascadeType.PERSIST) 
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="food_id")
    private Food food;
}