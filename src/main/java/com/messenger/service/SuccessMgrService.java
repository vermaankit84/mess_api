package com.messenger.service;

import com.messenger.bean.Vendor;
import com.messenger.constants.CacheConstants;
import com.messenger.events.EvtSuccessMgrReqRec;
import com.messenger.repository.SuccessEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("successMgrService")
public class SuccessMgrService {

    @Autowired
    private SuccessEventRepository successEventRepository = null;

    @Autowired
    private VendorService vendorService = null;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_EVENT_DIVISION_REQ_REC)
    public List<EvtSuccessMgrReqRec> getSuccessEventDetails() {
        return successEventRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_EVENT_DIVISION_REQ_REC, key = "#vendorId")
    public List<EvtSuccessMgrReqRec> getSuccessEventDetails(final int vendorId) {
        final Vendor vendor = vendorService.getVendorDetails(vendorId);
        if (vendor == null) {
            throw new IllegalArgumentException("This vendor id [ " + vendorId + " ]  is invalid");
        }
        return successEventRepository.getSuccessEventDetails(vendor);
    }
}
