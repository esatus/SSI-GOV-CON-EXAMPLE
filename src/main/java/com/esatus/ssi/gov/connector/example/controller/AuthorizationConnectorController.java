package com.esatus.ssi.gov.connector.example.controller;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.esatus.ssi.shared.dbmodel.Membership;
import com.esatus.ssi.shared.exception.CustomSSIExceptionResponse;
import com.esatus.ssi.shared.exception.SSIException;
import com.esatus.ssi.shared.service.APITokenService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/connector/")
public class AuthorizationConnectorController {

  @Autowired
  private APITokenService tokenService;

  @PostMapping(value = "synchronize")
  @ApiResponses(value = {@ApiResponse(code = 415, message = "Unsupported media-type"),
      @ApiResponse(code = 400, message = "Bad request"),
      @ApiResponse(code = 403, message = "Token invalid", response = CustomSSIExceptionResponse.class),
      @ApiResponse(code = 500, message = "An unhandled server exception has occured.",
          response = CustomSSIExceptionResponse.class)})
  public Boolean synchronize(@RequestParam(name = "token", required = false) String token,
      @RequestBody Collection<Membership> memberships, HttpServletRequest request, HttpServletResponse response)
      throws SSIException {

    if (token == null) {
      tokenService.validateXAuthTokenHeader(request);
    } else {
      tokenService.validate(token);
    }

    log.info("Example synchronization triggered");

    // boolean result = authorizationService.synchronize(memberships);

    log.info("Example synchronization finished");

    // return result;
    return null;
  }
}
