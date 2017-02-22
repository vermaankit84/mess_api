package com.messenger.repository;

import com.messenger.bean.BroadcastTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BrdTempRepository extends JpaRepository <BroadcastTemp, Integer> {

    @Query("select e from  BroadcastTemp e where e.brdId = :brdId")
    List<BroadcastTemp> getBroadCastDetails(@Param("brdId") final int brdId) throws Exception;
}
