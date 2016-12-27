package com.messenger.service;


import com.messenger.bean.Division;
import com.messenger.constants.CacheConstants;
import com.messenger.events.EvtDivisionMgrReqRec;
import com.messenger.repository.EventRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ankverma on 12/16/2016.
 */
@Service("eventMgrService")
public class EventMgrService {

    private final Logger logger = Logger.getLogger(EventMgrService.class.getName());

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DivisionService divisionService;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, timeout = 30)
    @Cacheable(cacheNames = CacheConstants.STR_EVENT_DIVISION_REQ_REC, key = "#divisionName")
    public List<EvtDivisionMgrReqRec> getDivisionEventDetails(final String divisionName) throws Exception {
        List<EvtDivisionMgrReqRec> eventRepositoryList = null;

        final Division division = divisionService.getDivision(divisionName);
        if (division == null) {
            throw new Exception("This division [ " + divisionName + " ] will not exists");
        }
        try {
            eventRepositoryList = eventRepository.getEventDivision(division);
        } catch (Exception e) {
            logger.warn("Exception arises while getting division details [ " + divisionName + " ] ", e);
        }
        return eventRepositoryList;
    }

    @Cacheable(cacheNames = CacheConstants.STR_EVENT_DIVISION_REQ_REC)
    public List<EvtDivisionMgrReqRec> getDivisionEventDetails() {
        return eventRepository.findAll();
    }

}
