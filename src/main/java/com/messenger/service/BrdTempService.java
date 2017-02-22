package com.messenger.service;

import com.messenger.bean.BroadcastTemp;
import com.messenger.constants.CacheConstants;
import com.messenger.repository.BrdTempRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service("brdTempService")
public class BrdTempService {

    private final Logger logger = Logger.getLogger(BrdTempService.class.toString());

    @Autowired
    private BrdTempRepository brdTempRepository;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_BUFFER_CACHE_CONSTANTS, key = "#brdId")
    public void insertBroadcastTempDetails(final String filePath , final String brdId) throws Exception {
        final List<BroadcastTemp> brdTempList = new ArrayList<>();
        String[] data = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8").split("\n");
        int counter = 0;
        for (final String d : data) {
            String [] messData = d.split(",");
            final BroadcastTemp broadcastTemp = new BroadcastTemp();
            broadcastTemp.setMessageType(messData[0]);
            broadcastTemp.setDestination(messData[1]);
            broadcastTemp.setMessageText(messData[2]);
            broadcastTemp.setMessagePriority(messData[3]);
            broadcastTemp.setIntFlag(messData[4].trim());
            broadcastTemp.setBrdId(brdId);
            counter = counter + 1;
            if (counter == 1000) {
                brdTempRepository.save(brdTempList);
                brdTempList.clear();
            }
            brdTempList.add(broadcastTemp);
        }
        brdTempRepository.save(brdTempList);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BUFFER_CACHE_CONSTANTS , key = "#brdId")
    public List<BroadcastTemp> getBroadCastDetails (final int brdId) throws Exception {
        return brdTempRepository.getBroadCastDetails(brdId);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_BUFFER_CACHE_CONSTANTS , key = "#msgId")
    public BroadcastTemp getBroadCastDetail(final int msgId) throws Exception {
        return brdTempRepository.findOne(msgId);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    @CachePut(value = CacheConstants.STR_BUFFER_CACHE_CONSTANTS, key = "#brdTemp.messageId")
    public BroadcastTemp updateBroadCastMessage(final BroadcastTemp brdTemp) throws Exception {
        final BroadcastTemp broadcastTemp = brdTempRepository.findOne(brdTemp.getMessageId());
        if (broadcastTemp == null) {
            throw new IllegalArgumentException("BroadCast details for Id [ " + brdTemp.getMessageId() + " ] does not exists");
        }
        broadcastTemp.setMessageType(brdTemp.getMessageType());
        broadcastTemp.setMessagePriority(brdTemp.getMessagePriority());
        broadcastTemp.setDestination(brdTemp.getDestination());
        broadcastTemp.setIntFlag(brdTemp.getIntFlag());
        broadcastTemp.setMessageText(brdTemp.getMessageText());
        return brdTempRepository.saveAndFlush(broadcastTemp);
    }
}
