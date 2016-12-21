package com.messenger.controller;

import com.messenger.bean.Sender;
import com.messenger.service.SenderService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SenderController {

    private final Logger logger = Logger.getLogger(SenderController.class.getName());
    @Autowired
    private SenderService senderService = null;

    @RequestMapping(value = "/createSender", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createSender(@RequestBody final Sender sender) {
        return new ResponseEntity<String>("Sender has been created with [ " + senderService.createSender(sender) + " ] at [ " + Utilities.getLocalDateTime() + " ] ", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateSender", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateSender(@RequestBody final Sender sender) {
        logger.info("Division Details obtained [ " + sender + " ]  at  [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            senderService.updateSender(sender);
            responseEntity = new ResponseEntity<String>("Division will has been updated with [ " + sender.getSenderName() + " ] ", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while saving Division [ " + sender.getSenderName() + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + sender.getSenderName() + " ] ", e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/getSenderDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Sender>> getSenderDetails() {
        return new ResponseEntity<>(senderService.getSenderList(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getSenderDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Sender> getSenderDetails(@PathVariable int id) {
        return new ResponseEntity<>(senderService.getSenderList(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/deleteSender/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> deleteSender(@PathVariable int id) {
        senderService.delete(id);
        return new ResponseEntity<>("This Sender " + id + " has been deleted successfully", HttpStatus.CREATED);
    }

}
