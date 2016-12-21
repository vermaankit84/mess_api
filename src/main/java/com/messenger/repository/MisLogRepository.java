package com.messenger.repository;

import com.messenger.bean.RecLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MisLogRepository extends JpaRepository<RecLog, String> {
}
