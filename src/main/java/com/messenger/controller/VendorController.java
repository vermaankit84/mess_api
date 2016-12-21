package com.messenger.controller;

import com.messenger.bean.Vendor;
import com.messenger.service.VendorService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    private final Logger logger = Logger.getLogger(VendorController.class.getName());
    @Autowired
    private VendorService vendorService = null;

    @RequestMapping(value = "/createVendor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createVendor(@RequestBody Vendor vendor) {
        logger.info("Vendor Details Obtained for [ " + vendor + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<String>("Vendor Has Been Created with Id [ " + vendorService.createVendor(vendor).getId() + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while saving Vendor [ " + vendor + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + vendor + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/updateVendor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateVendor(@RequestBody Vendor vendor) {
        logger.info("vendor details obtained for update [ " + vendor + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            vendorService.updateVendor(vendor);
            responseEntity = new ResponseEntity<String>("Vendor details [ " + vendor.getId() + " ] has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while updating Vendor [ " + vendor + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + vendor + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/vendorDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Vendor>> getVendorDetails() {
        return new ResponseEntity<List<Vendor>>(vendorService.getVendorDetails(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/deleteVendors/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<String> deleteVendor(@PathVariable final int id) {
        ResponseEntity<String> responseEntity = null;
        try {
            vendorService.delete(id);
            responseEntity = new ResponseEntity<>("Vendor with id [ " + id + " ] deleted successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>("exception arises while delete Vendor [ " + id + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while delete Vendor [ " + id + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/vendorDetail/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Vendor> getVendorDetails(@PathVariable final int id) {
        return new ResponseEntity<Vendor>(vendorService.getVendorDetails(id), HttpStatus.FOUND);
    }
}
