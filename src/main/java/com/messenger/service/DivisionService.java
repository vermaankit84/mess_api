package com.messenger.service;

import com.messenger.bean.Division;
import com.messenger.bean.Vendor;
import com.messenger.constants.CacheConstants;
import com.messenger.property.Config;
import com.messenger.repository.DivisionRepository;
import com.messenger.repository.VendorRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("divisionService")
public class DivisionService {
    private final Logger logger = Logger.getLogger(DivisionService.class.getName());
    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private Config config = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_DIVISION_CACHE_CONSTANTS, key = "#division.divisionName")
    public Division createDivision(final Division division) throws Exception {
        final Division d = divisionRepository.saveAndFlush(division);
        Utilities.refresh(config.getValue("receiver_division_urls").split(","));
        return d;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_DIVISION_CACHE_CONSTANTS, allEntries = true, key = "#division.divisionName")
    @CachePut(value = CacheConstants.STR_DIVISION_CACHE_CONSTANTS, key = "#division.divisionName")
    public void updateDivision(final Division division) throws Exception {
        final Division d = divisionRepository.findOne(division.getDivisionName());
        if (d == null) {
            throw new Exception("Division [ " + division + " ] does not exists");
        }
        d.setDivisionName(StringUtils.isNotEmpty(division.getDivisionName()) ? division.getDivisionName() : d.getDivisionName());
        d.setDivisionPassword(StringUtils.isNotEmpty(division.getDivisionPassword()) ? division.getDivisionPassword() : d.getDivisionPassword());
        divisionRepository.saveAndFlush(d);
        Utilities.refresh(config.getValue("receiver_division_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_DIVISION_CACHE_CONSTANTS)
    public List<Division> getDivision() {
        return divisionRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_DIVISION_CACHE_CONSTANTS)
    public Division getDivision(final String divisionName) {
        return divisionRepository.findOne(divisionName);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_DIVISION_CACHE_CONSTANTS, allEntries = true, key = "#id")
    public void deleteDivision(final String divisionId) {
        divisionRepository.delete(divisionId);
        Utilities.refresh(config.getValue("receiver_division_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateDivisionVendorMapping(final String divisionId, final Set<Integer> vendorNameSet) {
        final Division division = divisionRepository.findOne(divisionId);
        if (division == null) {
            throw new IllegalArgumentException("This Division Name Does not exists [ " + divisionId + " ] ");
        }
        final Set<Vendor> vendorList = new HashSet<>();
        for (final int vendorId : vendorNameSet) {
            vendorList.add(vendorRepository.findOne(vendorId));
        }
        division.setVendorSet(vendorList);
        divisionRepository.saveAndFlush(division);
    }
}
