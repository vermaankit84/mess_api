package com.messenger.service;

import com.messenger.bean.Sender;
import com.messenger.repository.SenderRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SenderService {

    @Autowired
    private SenderRepository senderRepository = null;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    public List<Sender> getSenderList() {
        return senderRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30, readOnly = true)
    public Sender getSenderList(final int senderId) {
        return senderRepository.findOne(senderId);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Sender createSender(final Sender sender) {
        return senderRepository.saveAndFlush(sender);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public void delete(final int id) {
        senderRepository.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateSender(final Sender sender) {
        final Sender s = senderRepository.findOne(sender.getId());
        s.setSenderName(StringUtils.isNotBlank(sender.getSenderName()) ? sender.getSenderName() : s.getSenderName());
        s.setDivision(sender.getDivision() != null ? sender.getDivision() : s.getDivision());
        s.setIsSenderActive(sender.getIsSenderActive() != 0 ? sender.getIsSenderActive() : s.getIsSenderActive());
        senderRepository.saveAndFlush(s);
    }
}
