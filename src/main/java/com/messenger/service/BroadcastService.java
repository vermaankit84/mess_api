package com.messenger.service;

import com.messenger.bean.Broadcast;
import com.messenger.bean.Division;
import com.messenger.bean.Sender;
import com.messenger.constants.CacheConstants;
import com.messenger.repository.BroadcastRepository;
import com.messenger.request.BroadcastRequest;
import com.messenger.types.VendorOrigin;
import com.messenger.util.Utilities;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("broadcastService")
public class BroadcastService {

    private final Logger logger = Logger.getLogger(BroadcastService.class.getName());

    @Autowired
    private BroadcastRepository broadcastRepository;

    @Autowired
    private DivisionService divisionService;

    @Autowired
    private SenderService senderService;

    @Autowired
    private BrdTempService brdTempService;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS, key = "#broadcastRequest.brdId")
    public String createBroadCast(final BroadcastRequest broadcastRequest) throws Exception {
        logger.info("BroadCast details obtained [ " + broadcastRequest + " ] at [ " + Utilities.getLocalDateTime() + " ] ");
        final Division division = divisionService.getDivision(broadcastRequest.getDivision());
        if (division == null) {
            throw new IllegalArgumentException("Division [ " + broadcastRequest.getDivision() + " ]  does not exists");
        }

        final Sender sender = senderService.getSender(broadcastRequest.getSender());
        if (sender == null) {
            throw new IllegalArgumentException("Sender Id [ " + broadcastRequest.getSender() + " ]  does not exists");
        }
        final VendorOrigin vendorOrigin = VendorOrigin.valueOf(broadcastRequest.getVendorOrigin());

        if (vendorOrigin == null) {
            throw new IllegalArgumentException("Vendor Origin [ " + broadcastRequest.getVendorOrigin() + " ]  does not exists");
        }
        final Broadcast broadcast = new Broadcast();
        broadcast.setDivision(division);
        broadcast.setScheduledDate(broadcastRequest.getScheduledDate());
        broadcast.setSender(sender);
        broadcast.setStatus(broadcastRequest.getStatus());
        broadcast.setBrdCount(broadcastRequest.getBrdCount());
        broadcast.setVendorOrigin(vendorOrigin);
        final int brdId = broadcastRepository.save(broadcast).getBrdId();
        brdTempService.insertBroadcastTempDetails(broadcastRequest.getFilePath() , String.valueOf(brdId));
        return "Broadcast has been saved with Id [ " + brdId + " ] ";
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS)
    public List<BroadcastRequest> getBroadcastRequestBasedOnDivision(final Division division) throws Exception  {
        List<BroadcastRequest> broadcastRequestList = null;
        final List<Broadcast> broadcastList = broadcastRepository.getBroadcastRequestBasedOnDivision(division);
        if (broadcastList != null && !broadcastList.isEmpty()) {
            for (int i = 0; i < broadcastList.size(); i = i + 1) {
                if (broadcastRequestList == null) {
                    broadcastRequestList = new ArrayList<>();
                }
                final Broadcast broadcast = broadcastList.get(i);
                final BroadcastRequest broadcastRequest = new BroadcastRequest();
                broadcastRequest.setScheduledDate(broadcast.getScheduledDate());
                broadcastRequest.setBrdCount(broadcast.getBrdCount());
                broadcastRequest.setDivision(broadcast.getDivision().getDivisionName());
                broadcastRequest.setSender(broadcast.getSender().getId());
                broadcastRequest.setVendorOrigin(String.valueOf(broadcast.getVendorOrigin()));
                broadcastRequestList.add(broadcastRequest);
            }
        }
        return broadcastRequestList;
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS)
    public List<BroadcastRequest> getBroadcastRequestBasedOnSender(final Sender senderName) throws Exception  {
        List<BroadcastRequest> broadcastRequestList = null;
        final List<Broadcast> broadcastList = broadcastRepository.getBroadcastRequestBasedOnSender(senderName);
        if (broadcastList != null && !broadcastList.isEmpty()) {
            for (int i = 0; i < broadcastList.size(); i = i + 1) {
                if (broadcastRequestList == null) {
                    broadcastRequestList = new ArrayList<>();
                }
                final Broadcast broadcast = broadcastList.get(i);
                final BroadcastRequest broadcastRequest = new BroadcastRequest();
                broadcastRequest.setScheduledDate(broadcast.getScheduledDate());
                broadcastRequest.setBrdCount(broadcast.getBrdCount());
                broadcastRequest.setDivision(broadcast.getDivision().getDivisionName());
                broadcastRequest.setSender(broadcast.getSender().getId());
                broadcastRequest.setVendorOrigin(String.valueOf(broadcast.getVendorOrigin()));
                broadcastRequestList.add(broadcastRequest);
            }
        }
        return broadcastRequestList;
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS)
    public List<BroadcastRequest> getBroadcastRequestBasedOnStatus(final int status) throws Exception  {
        List<BroadcastRequest> broadcastRequestList = null;
        final List<Broadcast> broadcastList = broadcastRepository.getBroadcastRequestBasedOnStatus(status);
        if (broadcastList != null && !broadcastList.isEmpty()) {
            for (int i = 0; i < broadcastList.size(); i = i + 1) {
                if (broadcastRequestList == null) {
                    broadcastRequestList = new ArrayList<>();
                }
                final Broadcast broadcast = broadcastList.get(i);
                final BroadcastRequest broadcastRequest = new BroadcastRequest();
                broadcastRequest.setScheduledDate(broadcast.getScheduledDate());
                broadcastRequest.setBrdCount(broadcast.getBrdCount());
                broadcastRequest.setDivision(broadcast.getDivision().getDivisionName());
                broadcastRequest.setSender(broadcast.getSender().getId());
                broadcastRequest.setVendorOrigin(String.valueOf(broadcast.getVendorOrigin()));
                broadcastRequestList.add(broadcastRequest);
            }
        }
        return broadcastRequestList;
    }


    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS)
    public List<BroadcastRequest> getBroadcastRequestBasedOnVendorOrigin(final String vendorOrigin) throws Exception  {
        final VendorOrigin v = VendorOrigin.valueOf(vendorOrigin);
        if (v == null) {
            throw new IllegalArgumentException("Vendor Origin [ " + vendorOrigin + " ] does not exists");
        }
        List<BroadcastRequest> broadcastRequestList = null;
        final List<Broadcast> broadcastList = broadcastRepository.getBroadcastRequestBasedOnVendorOrigin(v);
        if (broadcastList != null && !broadcastList.isEmpty()) {
            for (int i = 0; i < broadcastList.size(); i = i + 1) {
                if (broadcastRequestList == null) {
                    broadcastRequestList = new ArrayList<>();
                }
                final Broadcast broadcast = broadcastList.get(i);
                final BroadcastRequest broadcastRequest = new BroadcastRequest();
                broadcastRequest.setScheduledDate(broadcast.getScheduledDate());
                broadcastRequest.setBrdCount(broadcast.getBrdCount());
                broadcastRequest.setDivision(broadcast.getDivision().getDivisionName());
                broadcastRequest.setSender(broadcast.getSender().getId());
                broadcastRequest.setVendorOrigin(String.valueOf(broadcast.getVendorOrigin()));
                broadcastRequestList.add(broadcastRequest);
            }
        }
        return broadcastRequestList;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS, allEntries = true, key = "#broadcastRequest.brdId")
    @CachePut(value = CacheConstants.STR_BROADCAST_CACHE_CONSTANTS, key = "#broadcastRequest.brdId")
    public Broadcast updateBroadCastDetails(final BroadcastRequest broadcastRequest) throws Exception {
        final Broadcast b = broadcastRepository.findOne(broadcastRequest.getBrdId());
        if (b == null) {
            throw new IllegalArgumentException("These broadcast details with id [ " + broadcastRequest.getBrdId() + " ]  does not exists");
        }
        b.setBrdId(broadcastRequest.getBrdId());
        b.setVendorOrigin(VendorOrigin.valueOf(broadcastRequest.getVendorOrigin()));
        b.setSender(senderService.getSender(broadcastRequest.getSender()));
        b.setDivision(divisionService.getDivision(broadcastRequest.getDivision()));
        b.setBrdCount(broadcastRequest.getBrdCount());
        b.setScheduledDate(broadcastRequest.getScheduledDate());
        b.setStatus(broadcastRequest.getStatus());
        return broadcastRepository.saveAndFlush(b);
    }
}
