package com.messenger.controller;

import com.messenger.data.ReportingData;
import com.messenger.events.EvtDivisionMgrReqRec;
import com.messenger.events.EvtSuccessMgrReqRec;
import com.messenger.service.EventMgrService;
import com.messenger.service.ReportingService;
import com.messenger.service.SuccessMgrService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportingController {

    private final Logger logger = Logger.getLogger(ReportingController.class.getName());
    @Autowired
    private EventMgrService eventMgrService = null;

    @Autowired
    private SuccessMgrService successMgrService = null;

    @Autowired
    private ReportingService reportingService = null;

    @RequestMapping(value = "/getDivisionEventDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<EvtDivisionMgrReqRec>> getDivisionEventDetails() {
        return new ResponseEntity<>(eventMgrService.getDivisionEventDetails(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getDivisionEventDetails/{divisionName}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<EvtDivisionMgrReqRec>> getDivisionEventDetails(@PathVariable String divisionName) throws Exception {
        return new ResponseEntity<>(eventMgrService.getDivisionEventDetails(divisionName), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getSuccessEventDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<EvtSuccessMgrReqRec>> getSuccessEventDetails() {
        return new ResponseEntity<>(successMgrService.getSuccessEventDetails(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getSuccessEventDetails/{vendorId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<EvtSuccessMgrReqRec>> getSuccessEventDetails(@PathVariable int vendorId) {
        return new ResponseEntity<>(successMgrService.getSuccessEventDetails(vendorId), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getReportDetails/{start}/{end}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<ReportingData>> getReportDetails(@PathVariable final int start, @PathVariable final int end) {
        logger.info("GetReport Details [ " + start + " ] end [ " + end + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        return new ResponseEntity<>(reportingService.getReportingData(start, end), HttpStatus.FOUND);
    }
}
