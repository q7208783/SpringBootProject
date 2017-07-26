package com.zcc.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcc.model.dao.Payment;

/**
 * Created by NCP-620 on 2017/7/10.
 */
//
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Payment findPaymentById(Long id);
	List<Payment> findByUsername(String username);
}
