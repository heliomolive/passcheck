package hmo.passcheck.domain.exception;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends PasscheckException {

    @Builder
    public InternalErrorException(String userMessage, String developerMessage, Throwable cause) {
        super(userMessage, developerMessage, cause);
    }
}
