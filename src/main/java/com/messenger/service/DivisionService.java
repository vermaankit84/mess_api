package com.messenger.service;

import com.messenger.bean.Division;
import com.messenger.repository.DivisionRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("divisionService")
public class DivisionService {
    private final Logger logger = Logger.getLogger(DivisionService.class.getName());
    @Autowired
    private DivisionRepository divisionRepository;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Division createDivision(final Division division) throws Exception {
        final Division d = divisionRepository.saveAndFlush(division);
        Utilities.refresh(System.getenv("receiver_division_urls").split(","));
        return d;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateDivision(final Division division) throws Exception {
        final Division d = divisionRepository.findOne(division.getDivisionName());
        if (d == null) {
            throw new Exception("Division [ " + division + " ] does not exists");
        }
        d.setDivisionName(StringUtils.isNotEmpty(division.getDivisionName()) ? division.getDivisionName() : d.getDivisionName());
        d.setDivisionPassword(StringUtils.isNotEmpty(division.getDivisionPassword()) ? division.getDivisionPassword() : d.getDivisionPassword());
        divisionRepository.saveAndFlush(d);
        Utilities.refresh(System.getenv("receiver_division_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public List<Division> getDivision() {
        return divisionRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Division getDivision(final String divisionName) {
        return divisionRepository.findOne(divisionName);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public void deleteDivision(final String divisionId) {
        divisionRepository.delete(divisionId);
        Utilities.refresh(System.getenv("receiver_division_urls").split(","));
    }
}
