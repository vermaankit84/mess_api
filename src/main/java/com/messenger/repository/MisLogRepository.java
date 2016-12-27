package com.messenger.repository;

import com.messenger.bean.RecLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MisLogRepository extends JpaRepository<RecLog, String> {

    @Query("select e from RecLog e where e.destination = :destination")
    public Page<RecLog> getPageBasedMobile(final Pageable pageable, @Param("destination") final String destination);
}
