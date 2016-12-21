package com.messenger.repository;

import com.messenger.bean.Division;
import com.messenger.events.EvtDivisionMgrReqRec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EvtDivisionMgrReqRec, Integer> {

    @Query("select e from  EvtDivisionMgrReqRec e where e.division = :divName")
    List<EvtDivisionMgrReqRec> getEventDivision(@Param("divName") Division divName);

}
