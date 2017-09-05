package com.dish.ofm.service.codeChallenge083017.controller;

import com.dish.ofm.service.codeChallenge083017.service.CipherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeChallenge083017Controller {
    private static Logger LOGGER = LoggerFactory.getLogger(CodeChallenge083017Controller.class);
    private CipherService cipherService;

    @Autowired
    public CodeChallenge083017Controller(
        CipherService cipherService
    ) {
        this.cipherService = cipherService;
    }

    @RequestMapping(value = "/encode", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "challenge", httpMethod = "PUT",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "encode ok", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity encode() {
        LOGGER.info("Received /encode");

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        LOGGER.info("Finished /encode with  responseEntity = {}", responseEntity);
        return responseEntity;
    }

    @RequestMapping(value = "/decode", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "challenge", httpMethod = "PUT",
        response = HttpStatus.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "decode ok", response = HttpStatus.class),
        @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity decode() {
        LOGGER.info("Received /decode");

        ResponseEntity<Void> responseEntity = ResponseEntity.ok().build();

        LOGGER.info("Finished /decode with  responseEntity = {}", responseEntity);
        return responseEntity;
    }
}
