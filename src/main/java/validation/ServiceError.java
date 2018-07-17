package validation;

import org.springframework.http.HttpStatus;

public interface ServiceError {


    String name();

    HttpStatus getStatus();

    String getDescription();
}
