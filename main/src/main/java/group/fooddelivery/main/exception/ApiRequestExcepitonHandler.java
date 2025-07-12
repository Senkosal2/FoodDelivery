package group.fooddelivery.main.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiRequestExcepitonHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException notFound) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.NOT_FOUND,
            404,
            notFound.getMessage(),
            ZonedDateTime.now());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
