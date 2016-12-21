package com.messenger.repository;

import com.messenger.bean.Vendor;
import com.messenger.events.EvtSuccessMgrReqRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuccessEventRepository extends JpaRepository<EvtSuccessMgrReqRec, Integer> {

    @Query("select e from EvtSuccessMgrReqRec e where e.vendor = :vendor")
    public List<EvtSuccessMgrReqRec> getSuccessEventDetails(@Param("vendor") final Vendor vendor);
}
