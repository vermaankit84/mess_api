package com.messenger.controller;

import com.messenger.bean.Template;
import com.messenger.service.TemplateService;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TemplateController {
    private final Logger logger = Logger.getLogger(TemplateController.class.getName());

    @Autowired
    private TemplateService templateService = null;

    @RequestMapping(value = "/createTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> createTemplate(@RequestBody final Template template) {
        logger.info("Template Details Obtained [ " + template + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = new ResponseEntity<>("Template Has Been Created with Id [ " + templateService.createTemplate(template).getId() + " ] ", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("exception arises while saving Template [ " + template + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving Vendor [ " + template + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/updateTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<String> updateTemplate(@RequestBody final Template template) {
        logger.info("Template details obtained for update [ " + template + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        ResponseEntity<String> responseEntity = null;
        try {
            templateService.updateTemplate(template);
            responseEntity = new ResponseEntity<>("Template details [ " + template.getId() + " ] has been updated successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("exception arises while updating template [ " + template + " ] exception details [" + e + " ] ", HttpStatus.BAD_REQUEST);
            logger.warn("exception arises while saving template [ " + template + " ] ", e);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/templateDetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<List<Template>> getTemplateDetails() {
        return new ResponseEntity<>(templateService.getTemplateList(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/templateDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Template> getTemplateDetails(@PathVariable final int id) {
        return new ResponseEntity<>(templateService.getTemplateDetail(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/deleteTemplate/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<String> deleteTemplate(@PathVariable final int id) {
        templateService.delete(id);
        return new ResponseEntity<>("Template with id [ " + id + " has been deleted successfully", HttpStatus.ACCEPTED);
    }
}
