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
    ResponseEntity<String> createVendor(@RequestBody final Buffer buffer) {
        logger.info("Buffer Details Obtained for Create [ " + buffer + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<String>("Buffer Has Been Created with Id [ " + bufferService.createBuffer(buffer).getId() + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while saving Vendor [ " + buffer + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving buffer details [ " + buffer + " ] ", e);
        }
        return responseEntity;
    }


    @RequestMapping(value = "/updateBuffer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateVendor(@RequestBody final Buffer buffer) {
        logger.info("Buffer Details Obtained for Update [ " + buffer + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            bufferService.updateBuffer(buffer);
            responseEntity = new ResponseEntity<String>("Buffer Has Been Updated with Id [ " + buffer.getId() + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while saving Vendor [ " + buffer + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while updating buffer details [ " + buffer + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getBufferDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Buffer>> getBufferDetails() {
        return new ResponseEntity<>(bufferService.getBufferDetails(), HttpStatus.ACCEPTED);
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
    ResponseEntity<String> deleteBuffer(@PathVariable final int id) {
        bufferService.deleteBuffer(id);
        return new ResponseEntity<>("Buffer with id [ " + id + " ] has been deleted successfully ", HttpStatus.ACCEPTED);
    }
}
