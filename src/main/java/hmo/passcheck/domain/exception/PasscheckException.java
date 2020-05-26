package hmo.passcheck.domain.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasscheckException extends RuntimeException {
    private String userMessage;
    private String developerMessage;

    public PasscheckException() {}

    public PasscheckException(String userMessage, String developerMessage, Throwable cause) {
        super(developerMessage, cause);
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }
}
