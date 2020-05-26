package hmo.passcheck.controller;

import hmo.passcheck.controller.request.VerifyPasswordRequest;
import hmo.passcheck.service.PassCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(produces = "application/json;charset=UTF-8")
@Setter
@RestController
@RequestMapping(produces = "application/json;charset=UTF-8")
public class PasscheckController {

    private static final String V1 = "/v1";

    @Autowired
    private PassCheckService passCheckService;

    @ApiOperation("Check if a password is valid according to the password class")
    @PostMapping(V1 + "/validate")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void validate(@RequestBody @Valid VerifyPasswordRequest request) {
        passCheckService.validate(request.getPassword(), request.getPasswordClass());
    }

}
