package hmo.passcheck.domain.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends PasscheckException {

    @Builder
    public NotFoundException(String userMessage, String developerMessage, Throwable cause) {
        super(userMessage, developerMessage, cause);
    }
}