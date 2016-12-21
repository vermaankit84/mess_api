package com.messenger.repository;

import com.messenger.bean.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
