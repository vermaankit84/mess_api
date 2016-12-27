package com.messenger.service;

import com.messenger.bean.Vendor;
import com.messenger.repository.VendorRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("vendorService")
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Vendor createVendor(final Vendor vendor) throws Exception {
        final Vendor v = vendorRepository.save(vendor);
        Utilities.refresh(System.getProperty("receiver_vendor_urls").split(","));
        Utilities.refresh(System.getProperty("sender_vendor_urls").split(","));
        return v;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateVendor(final Vendor vendor) throws Exception {
        final Vendor v = vendorRepository.getOne(vendor.getId());
        v.setVendorName(StringUtils.isNotEmpty(vendor.getVendorName()) ? vendor.getVendorName() : v.getVendorName());
        v.setVendorOrigin(vendor.getVendorOrigin() != null ? vendor.getVendorOrigin() : v.getVendorOrigin());
        v.setVendorType(vendor.getVendorType() != null ? vendor.getVendorType() : v.getVendorType());
        v.setVendorUrl(StringUtils.isNotEmpty(vendor.getVendorUrl()) ? vendor.getVendorUrl() : v.getVendorUrl());
        v.setVendorPriority(StringUtils.isNotEmpty(String.valueOf(vendor.getVendorPriority())) ? vendor.getVendorPriority() : v.getVendorPriority());
        v.setVendorStatus(StringUtils.isNotEmpty(String.valueOf(vendor.getVendorStatus())) ? vendor.getVendorStatus() : v.getVendorStatus());
        v.setVendorHeader(StringUtils.isNotEmpty(vendor.getVendorHeader()) ? vendor.getVendorHeader() : v.getVendorHeader());
        v.setVendorCredentials(StringUtils.isNotEmpty(vendor.getVendorCredentials()) ? vendor.getVendorCredentials() : v.getVendorCredentials());
        vendorRepository.saveAndFlush(v);
        Utilities.refresh(System.getProperty("receiver_vendor_urls").split(","));
        Utilities.refresh(System.getProperty("sender_vendor_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Vendor> getVendorDetails() {
        return vendorRepository.findAll().parallelStream().filter(vendor -> vendor.getVendorStatus() == 1).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(int id) throws Exception {
        vendorRepository.delete(id);
        Utilities.refresh(System.getProperty("receiver_vendor_urls").split(","));
        Utilities.refresh(System.getProperty("sender_vendor_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Vendor getVendorDetails(int id) {
        return vendorRepository.findOne(id);
    }
}
