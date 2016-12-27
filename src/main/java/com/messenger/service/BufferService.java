package com.messenger.service;

import com.messenger.bean.Buffer;
import com.messenger.repository.BufferRepository;
import com.messenger.util.Utilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("bufferService")
public class BufferService {

    private final Logger logger = Logger.getLogger(BufferService.class.getName());
    @Autowired
    private BufferRepository bufferRepository;

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Buffer createBuffer(final Buffer buffer) throws Exception {
        final Buffer b = bufferRepository.saveAndFlush(buffer);
        Utilities.refresh(System.getProperty("receiver_buffer_urls").split(","));
        return b;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class, timeout = 30)
    public void updateBuffer(final Buffer buffer) {
        final Buffer b = bufferRepository.findOne(buffer.getId());
        b.setBufferName(StringUtils.isNotEmpty(buffer.getBufferName()) ? buffer.getBufferName() : b.getBufferName());
        b.setBufferStatus(StringUtils.isNotEmpty(buffer.getBufferStatus()) ? buffer.getBufferStatus() : b.getBufferStatus());
        b.setBufferTable(StringUtils.isNotEmpty(buffer.getBufferTable()) ? buffer.getBufferTable() : b.getBufferTable());
        b.setBufferType(buffer.getBufferType() != null ? buffer.getBufferType() : b.getBufferType());
        bufferRepository.saveAndFlush(b);
        Utilities.refresh(System.getProperty("receiver_buffer_urls").split(","));
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public List<Buffer> getBufferDetails() {
        return bufferRepository.findAll().parallelStream().filter(buffer -> buffer.getBufferStatus().equalsIgnoreCase("1")).collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public Buffer getBufferDetails(int id) {
        return bufferRepository.findOne(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, noRollbackFor = Exception.class, timeout = 30)
    public void deleteBuffer(int id) {
        final Buffer buffer = bufferRepository.findOne(id);
        if (buffer == null) {
            throw new IllegalArgumentException("This Buffer id [ " + id + " ] doe not exists");
        }
        bufferRepository.delete(id);
        Utilities.refresh(System.getProperty("receiver_buffer_urls").split(","));
    }
}
