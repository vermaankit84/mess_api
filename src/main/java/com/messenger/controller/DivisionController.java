package com.messenger.controller;

import com.messenger.bean.Division;
import com.messenger.service.DivisionService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class DivisionController {

    private final Logger logger = Logger.getLogger(DivisionController.class.getName());
    @Autowired
    private DivisionService divisionService;

    @RequestMapping(value = "/createDivision", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createDivision(@RequestBody Division division) {
        logger.info("Division Details obtained [ " + division + " ]  at  [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<String>("Division will has been created with [ " + divisionService.createDivision(division) + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while saving Division [ " + division + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + division + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/updateDivision", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateDivision(@RequestBody Division division) {
        logger.info("Division Details obtained [ " + division + " ]  at  [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            divisionService.updateDivision(division);
            responseEntity = new ResponseEntity<>("Division will has been updated with [ " + division.getDivisionName() + " ] ", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("exception arises while saving Division [ " + division + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + division + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/getDivisionDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Division>> getDivisionDetails() {
        return new ResponseEntity<>(divisionService.getDivision(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getDivisionDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Division> getDivisionDetails(@PathVariable String id) {
        return new ResponseEntity<>(divisionService.getDivision(id), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteDivision/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> deleteDivision(@PathVariable String id) {
        divisionService.deleteDivision(id);
        return new ResponseEntity<>("This division " + id + " has been deleted successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/updateDivisionVendorMapping/{divisionId}", params = "vendorNameList", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateDivisionVendorMapping(@PathVariable final String divisionId, @RequestParam final Set<Integer> vendorNameList) {
        divisionService.updateDivisionVendorMapping(divisionId, vendorNameList);
        return new ResponseEntity<>("Division [" + divisionId + " ] has been updated for vendor name list [ " + vendorNameList + " ] successfully", HttpStatus.ACCEPTED);
    }
}
