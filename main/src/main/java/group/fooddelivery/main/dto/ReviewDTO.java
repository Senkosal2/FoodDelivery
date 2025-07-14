package group.fooddelivery.main.dto;

import java.sql.Date;
import java.sql.Time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Required field!")
    @NotNull(message = "Required field!")
    @NotEmpty(message = "Required field!")
    private String comments;

    @NotNull(message = "Required field!")
    @Size(min = 0, max = 5, message = "Rating must be between 0 and 5")
    private float rating;

    @NotNull(message = "Required field!")
    private UserDetailDTO userDTO;

    @NotNull(message = "Required field!")
    private FoodDTO foodDTO;
}