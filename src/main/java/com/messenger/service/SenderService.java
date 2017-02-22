package com.messenger.service;

import com.messenger.bean.Sender;
import com.messenger.constants.CacheConstants;
import com.messenger.property.Config;
import com.messenger.repository.SenderRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenderService {
    private final Logger logger = Logger.getLogger(SenderService.class.getName());
    @Autowired
    private SenderRepository senderRepository = null;

    @Autowired
    private Config config = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_SENDER_CACHE_CONSTANTS)
    public List<Sender> getSenderList() {
        return senderRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_SENDER_CACHE_CONSTANTS, key = "#senderId")
    public Sender getSender(final int senderId) {
        return senderRepository.findOne(senderId);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    @Cacheable(cacheNames = CacheConstants.STR_SENDER_CACHE_CONSTANTS, key = "#senderName")
    public List<Sender> getSenderName(final String senderName) {
        return senderRepository.findAll().parallelStream().filter(s -> s.getSenderName().equals(senderName)).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_SENDER_CACHE_CONSTANTS, key = "#sender.id")
    public Sender createSender(final Sender sender) {
        final Sender s = senderRepository.saveAndFlush(sender);
        Utilities.refresh(config.getValue("receiver_sender_urls").split(","));
        Utilities.refresh(config.getValue("sender_sender_urls").split(","));
        return s;
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_SENDER_CACHE_CONSTANTS, allEntries = true, key = "#id")
    public void delete(final int id) {
        senderRepository.delete(id);
        Utilities.refresh(config.getValue("receiver_sender_urls").split(","));
        Utilities.refresh(config.getValue("sender_sender_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    @CacheEvict(value = CacheConstants.STR_SENDER_CACHE_CONSTANTS, allEntries = true, key = "#sender.id")
    @CachePut(value = CacheConstants.STR_SENDER_CACHE_CONSTANTS, key = "#sender.id")
    public void updateSender(final Sender sender) {
        final Sender s = senderRepository.findOne(sender.getId());
        s.setSenderName(StringUtils.isNotBlank(sender.getSenderName()) ? sender.getSenderName() : s.getSenderName());
        s.setDivision(sender.getDivision() != null ? sender.getDivision() : s.getDivision());
        s.setIsSenderActive(sender.getIsSenderActive() != 0 ? sender.getIsSenderActive() : s.getIsSenderActive());
        senderRepository.saveAndFlush(s);
        Utilities.refresh(config.getValue("receiver_sender_urls").split(","));
        Utilities.refresh(config.getValue("sender_sender_urls").split(","));
    }
}
