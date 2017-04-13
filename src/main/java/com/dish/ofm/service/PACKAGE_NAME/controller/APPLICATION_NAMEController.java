package com.dish.ofm.service.PACKAGE_NAME.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/REST_ENDPOINT", description = "PROJECT_TITLE")
public class APPLICATION_NAMEController {

    private static Logger LOGGER = LoggerFactory.getLogger(APPLICATION_NAMEController.class);

    @RequestMapping(value = "/REST_ENDPOINT", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "REST_ENDPOINT_CAMEL_CASE", httpMethod = "GET",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "REST_ENDPOINT_SUCCESS_MESSAGE", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity REST_ENDPOINT_CAMEL_CASE() {
        LOGGER.info("Received /REST_ENDPOINT");

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        LOGGER.info("Finished /REST_ENDPOINT with  responseEntity = {}", responseEntity);
        return responseEntity;
    }
}
