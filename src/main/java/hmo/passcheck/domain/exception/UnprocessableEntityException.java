package hmo.passcheck.domain.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends PasscheckException{

    @Builder
    public UnprocessableEntityException(String userMessage, String developerMessage, Throwable cause) {
        super(userMessage, developerMessage, cause);
    }
}
