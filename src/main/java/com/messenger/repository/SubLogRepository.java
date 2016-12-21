package com.messenger.repository;

import com.messenger.bean.SubLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubLogRepository extends PagingAndSortingRepository<SubLog, String> {
}
