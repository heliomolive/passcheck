package hmo.passcheck.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@ApiModel("Error Response")
@Data
@Builder
public class PasscheckErrorResponse  {

    @ApiModelProperty("Developer message, provided for development support")
    private String developerMessage;

    @ApiModelProperty("User message, can be exhibited for final user")
    private String userMessage;
}
