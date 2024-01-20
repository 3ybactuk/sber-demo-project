package com.sber.demo.sberdemoproject.internal.repository.phoneservice;

import com.sber.demo.sberdemoproject.internal.repository.phoneservice.entities.DBPhoneItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<DBPhoneItem, Long> {
}
