package com.messenger.repository;

import com.messenger.bean.Broadcast;
import com.messenger.bean.Division;
import com.messenger.bean.Sender;
import com.messenger.types.VendorOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("broadcastRepository")
public interface BroadcastRepository extends JpaRepository<Broadcast, Integer> {
    @Query("select e from  Broadcast e where e.division = :divisionName")
    List<Broadcast> getBroadcastRequestBasedOnDivision(@Param("divisionName") Division divisionName);

    @Query("select e from  Broadcast e where e.sender = :senderName")
    List<Broadcast> getBroadcastRequestBasedOnSender(@Param("senderName") Sender senderName);

    @Query("select e from  Broadcast e where e.status = :status")
    List<Broadcast> getBroadcastRequestBasedOnStatus(@Param("status") int status);

    @Query("select e from  Broadcast e where e.vendorOrigin = :vendorOrigin")
    List<Broadcast> getBroadcastRequestBasedOnVendorOrigin(@Param("vendorOrigin") VendorOrigin vendorOrigin);
}
