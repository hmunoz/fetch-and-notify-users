package com.fetch.users.controller;

import com.fetch.users.domain.User;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

/**
 * Created by RaoSa on 10/17/2015.
 */
@RestController
@RequestMapping("/franchisees/anurag/customers")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@PathVariable("id") String id, @RequestBody User user) {
        logger.debug("I am in the controller and got ID: " + id.toString());
        logger.debug("I am in the controller and got user name: " + user.getGeniusname());
        return new ResponseEntity(HttpStatus.CREATED);

    }
}
