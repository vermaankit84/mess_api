package com.messenger.controller;

import com.messenger.bean.Buffer;
import com.messenger.service.BufferService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BufferController {

    private final Logger logger = Logger.getLogger(BufferController.class.getName());
    @Autowired
    private BufferService bufferService;

    @RequestMapping(value = "/createBuffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createBuffer(@RequestBody final Buffer buffer) {
        logger.info("Buffer Details Obtained for Create [ " + buffer + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(Utilities.convertToJson(String.valueOf("Buffer has been saved with id " + bufferService.createBuffer(buffer).getId()), "createBuffer"), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(Utilities.convertToJson("exception arises while creating buffer details [ " + buffer.getBufferTable() + " ] ", "createBuffer"), HttpStatus.INTERNAL_SERVER_ERROR);
            logger.warn("exception arises while saving buffer details [ " + buffer + " ] ", e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/updateBuffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<Buffer> updateBuffer(@RequestBody final Buffer buffer) {
        logger.info("Buffer Details Obtained for Update [ " + buffer + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<Buffer> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(bufferService.updateBuffer(buffer), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>((Buffer) null, HttpStatus.INTERNAL_SERVER_ERROR);
            logger.warn("exception arises while updating buffer details [ " + buffer + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getBufferDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Buffer>> getBufferDetails() {
        final List<Buffer> bufferList = bufferService.getBufferDetails();
        logger.info("Buffer Details obtained [ " + bufferList + " ] ");
        return new ResponseEntity<>(bufferList, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getBufferDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Buffer> getBufferDetails(@PathVariable final int id) {
        return new ResponseEntity<>(bufferService.getBufferDetails(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/deleteBuffer/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<Buffer> deleteBuffer(@PathVariable final int id) {
        return new ResponseEntity<>(bufferService.deleteBuffer(id), HttpStatus.ACCEPTED);
    }
}
