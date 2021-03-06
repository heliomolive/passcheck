package hmo.passcheck.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel(description = "Verify password request")
@Data
@Builder
@AllArgsConstructor
public class VerifyPasswordRequest {

    @NotEmpty(message = "Required parameter: password")
    @ApiModelProperty(value = "Password", required = true)
    private String password;

    @NotEmpty(message = "Required parameter: passwordClass")
    @ApiModelProperty(value = "Password class acronym (example NCPWR)")
    private String passwordClass;
}
