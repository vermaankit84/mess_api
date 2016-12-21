package com.messenger.repository;

import com.messenger.bean.Buffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BufferRepository extends JpaRepository<Buffer, Integer> {
}
