package group.fooddelivery.main.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import group.fooddelivery.main.dto.response.InvalidResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ErrorExceptionController implements ErrorController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/error")
    public ResponseEntity<InvalidResponse<List<String>>> routeNotFound(HttpServletRequest request) {
        
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (statusCode == null) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }

        InvalidResponse<List<String>> invalidResponse = new InvalidResponse<>();
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            invalidResponse = new InvalidResponse<>();
            invalidResponse.setStatus(HttpStatus.NOT_FOUND);
            invalidResponse.setStatusCode(404);
            // invalidResponse.setMessage("Current uri does not exist: " + request.getRequestURL());
            invalidResponse.setUri(List.of(
                "/api/users",
                "/api/addresses",
                "/api/foods",
                "/api/categories",
                "/api/orders",
                "/api/api/order-details",
                "/api/reviews",
                "/api/toppings",
                "/api/deliveries",
                "/api/delivery-details"
            ));
        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            invalidResponse = new InvalidResponse<>();
            invalidResponse.setStatus(HttpStatus.FORBIDDEN);
            invalidResponse.setStatusCode(403);
            invalidResponse.setMessage("Insignifiant authorization.");
        }

        return new ResponseEntity<>(invalidResponse, invalidResponse.getStatus());
    }
}
