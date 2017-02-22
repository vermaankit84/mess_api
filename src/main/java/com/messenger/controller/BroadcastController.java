package com.messenger.controller;

import com.messenger.bean.BroadcastTemp;
import com.messenger.bean.Division;
import com.messenger.bean.Sender;
import com.messenger.request.BroadcastRequest;
import com.messenger.service.BrdTempService;
import com.messenger.service.BroadcastService;
import com.messenger.service.DivisionService;
import com.messenger.service.SenderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BroadcastController {

    private final Logger logger = Logger.getLogger(BroadcastController.class.getName());

    @Autowired
    private BroadcastService broadcastService;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private SenderService senderService;

    @Autowired
    private BrdTempService brdTempService;

    @RequestMapping(value = "/createBroadCast", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createBroadCast(@RequestBody final BroadcastRequest broadcastRequest) {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>(broadcastService.createBroadCast(broadcastRequest), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(broadcastRequest.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            logger.warn("exception arises while saving broadcast details [ " + broadcastRequest + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getBroadcastRequestBasedOnDivision/{divisionName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<BroadcastRequest>> getBroadcastRequestBasedOnDivision(@PathVariable final String divisionName) throws Exception {
        final Division division = divisionService.getDivision(divisionName);
        if (division == null) {
            throw new IllegalArgumentException("Division [ " + divisionName + " ] will not exists");
        }
        return new ResponseEntity<>(broadcastService.getBroadcastRequestBasedOnDivision(division), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getBroadcastRequestBasedOnSender/{senderName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<BroadcastRequest>> getBroadcastRequestBasedOnSender(@PathVariable final String senderName) throws Exception {
        final List<Sender> senderList = senderService.getSenderName(senderName);
        if (senderList == null || senderList.isEmpty()) {
            throw new IllegalArgumentException("Sender [ " + senderName + " ] will not exists");
        }
        Sender sender = senderList.get(0);
        return new ResponseEntity<>(broadcastService.getBroadcastRequestBasedOnSender(sender), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getBroadcastRequestBasedOnStatus/{status}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<BroadcastRequest>> getBroadcastRequestBasedOnStatus(@PathVariable final int status) throws Exception  {
        return new ResponseEntity<>(broadcastService.getBroadcastRequestBasedOnStatus(status), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getBroadcastRequestBasedOnVendorOrigin/{vendorOrigin}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<BroadcastRequest>> getBroadcastRequestBasedOnVendorOrigin(@PathVariable final String vendorOrigin) throws Exception  {
        return new ResponseEntity<>(broadcastService.getBroadcastRequestBasedOnVendorOrigin(vendorOrigin), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getBroadCastDetails/{brdId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<BroadcastTemp>> getBroadCastDetails(@PathVariable final int brdId) throws Exception  {
        return new ResponseEntity<>(brdTempService.getBroadCastDetails(brdId), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/getBroadCastDetail/{msgId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<BroadcastTemp> getBroadCastDetail(@PathVariable final int msgId) throws Exception  {
        return new ResponseEntity<>(brdTempService.getBroadCastDetail(msgId), HttpStatus.FOUND);
    }
}
