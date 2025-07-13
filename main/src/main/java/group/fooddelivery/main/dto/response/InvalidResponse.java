package group.fooddelivery.main.dto.response;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidResponse<T> {
    private HttpStatus status;
    private int statusCode;
    private ZonedDateTime time;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T uri;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T fields;

    public InvalidResponse() {
        time = ZonedDateTime.now();
    }
}
