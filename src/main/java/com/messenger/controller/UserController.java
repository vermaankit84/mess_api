package com.messenger.controller;

import com.messenger.bean.UserDetails;
import com.messenger.service.UserService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final Logger logger = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService = null;


    @RequestMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createUser(@RequestBody final UserDetails userDetails) {
        logger.info("User Details Obtained [ " + userDetails + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>("User Has Been Created with Id [ " + userService.createUser(userDetails).getUserId() + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("exception arises while saving user [ " + userDetails + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving user [ " + userDetails + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateUser(@RequestBody final UserDetails userDetails) {
        logger.info("Update details obtained for update [ " + userDetails + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            userService.updateTemplate(userDetails);
            responseEntity = new ResponseEntity<>("Template details [ " + userDetails.getUserId() + " ] has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("exception arises while updating user details [ " + userDetails + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving user details [ " + userDetails + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/userDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<UserDetails>> getUserDetails() {
        return new ResponseEntity<>(userService.getUserDetails(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/userDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<UserDetails> getUserDetails(@PathVariable final int id) {
        return new ResponseEntity<>(userService.getUserDetails(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> deleteUser(@PathVariable final int id) {
        userService.delete(id);
        return new ResponseEntity<>("User with id [ " + id + " ] has been deleted successfully", HttpStatus.ACCEPTED);
    }
}
