package com.messenger.service;

import com.messenger.bean.RecLog;
import com.messenger.bean.SubLog;
import com.messenger.data.ReportingData;
import com.messenger.repository.MisLogRepository;
import com.messenger.repository.SubLogRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service("reportingService")
public class ReportingService {

    @Autowired
    private MisLogRepository misLogRepository = null;

    @Autowired
    private SubLogRepository subLogRepository = null;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true, timeout = 30, rollbackFor = Exception.class)
    public List<ReportingData> getReportingData(int start, int end) {
        final Page<RecLog> misLogList = misLogRepository.findAll(new PageRequest(start, end));
        final List<ReportingData> reportingDataList = new ArrayList<>();
        if (misLogList != null) {
            misLogList.forEach(new Consumer<RecLog>() {
                @Override
                public void accept(final RecLog recLog) {
                    final SubLog subLog = subLogRepository.findOne(recLog.getMessageId());
                    if (subLog != null) {
                        final ReportingData reportingData = new ReportingData(recLog.getMessageId(), recLog.getAppResponseId(), recLog.getDivision().getDivisionName(),
                                recLog.getDelivaryFlag(), recLog.getMsgType(), recLog.getDestination(), recLog.getPriority(), recLog.getMessageText(),
                                recLog.getSmslength(), recLog.getDndFlag(), recLog.getReqDumpdate(), recLog.getVendorOrigin().name(), subLog.getVendor().getVendorName(), subLog.getStatus(),
                                subLog.getStatusDesc(), subLog.getVendorResponseId(), subLog.getVendorSubmitDateTime(), subLog.getRequestDumpDateTime(), subLog.getVendor().getVendorName());
                        reportingDataList.add(reportingData);
                    } else {
                        final ReportingData reportingData = new ReportingData(recLog.getMessageId(), recLog.getAppResponseId(), recLog.getDivision().getDivisionName(),
                                recLog.getDelivaryFlag(), recLog.getMsgType(), recLog.getDestination(), recLog.getPriority(), recLog.getMessageText(),
                                recLog.getSmslength(), recLog.getDndFlag(), recLog.getReqDumpdate(), recLog.getVendorOrigin().name(), StringUtils.EMPTY, "B",
                                "SUBMITTED TO BUFFER", StringUtils.EMPTY, null, null, StringUtils.EMPTY);
                        reportingDataList.add(reportingData);
                    }
                }
            });
        }
        return reportingDataList;
    }
}
