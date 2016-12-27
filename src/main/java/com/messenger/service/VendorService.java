package com.messenger.service;

import com.messenger.bean.Vendor;
import com.messenger.constants.CacheConstants;
import com.messenger.property.Config;
import com.messenger.repository.VendorRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("vendorService")
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository = null;

    @Autowired
    private Config config = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_TEMPLATE_CACHE_CONSTANTS, key = "#vendor.id")
    public Vendor createVendor(final Vendor vendor) throws Exception {
        final Vendor v = vendorRepository.save(vendor);
        Utilities.refresh(config.getValue("receiver_vendor_urls").split(","));
        Utilities.refresh(config.getValue("sender_vendor_urls").split(","));
        return v;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_VENDOR_CACHE_CONSTANTS, allEntries = true, key = "#vendor.id")
    @CachePut(value = CacheConstants.STR_VENDOR_CACHE_CONSTANTS, key = "#vendor.id")
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
        Utilities.refresh(config.getValue("receiver_vendor_urls").split(","));
        Utilities.refresh(config.getValue("sender_vendor_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_VENDOR_CACHE_CONSTANTS)
    public List<Vendor> getVendorDetails() {
        return vendorRepository.findAll().parallelStream().filter(vendor -> vendor.getVendorStatus() == 1).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @CacheEvict(value = CacheConstants.STR_VENDOR_CACHE_CONSTANTS, allEntries = true, key = "#id")
    public void delete(int id) throws Exception {
        vendorRepository.delete(id);
        Utilities.refresh(config.getValue("receiver_vendor_urls").split(","));
        Utilities.refresh(config.getValue("sender_vendor_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_VENDOR_CACHE_CONSTANTS, key = "#id")
    public Vendor getVendorDetails(int id) {
        return vendorRepository.findOne(id);
    }
}
