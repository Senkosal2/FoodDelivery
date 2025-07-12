package group.fooddelivery.main.dto;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private int id;
    private Date reviewDate;
    private Time reviewTime;
    private String comments;
    private float rating;
    private UserDTO userDTO;
    private FoodDTO foodDTO;
}