package com.dish.ofm.service.beaconsession.controller;

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
@Api(value = "/get-session", description = "Beacon Session")
public class BeaconSessionController {

    private static Logger LOGGER = LoggerFactory.getLogger(BeaconSessionController.class);

    @RequestMapping(value = "/get-session", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "getSession", httpMethod = "GET",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Get session success", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity getSession() {
        LOGGER.info("Received /get-session");

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        LOGGER.info("Finished /get-session with  responseEntity = {}", responseEntity);
        return responseEntity;
    }
}
