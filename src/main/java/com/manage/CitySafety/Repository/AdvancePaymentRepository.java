package com.manage.CitySafety.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.AdvancePayment;
import com.manage.CitySafety.Entity.SupervisorEntry;

@Repository
public interface AdvancePaymentRepository extends JpaRepository<AdvancePayment, Long>{

	List<AdvancePayment> findByDateBetween(LocalDate startDate,LocalDate endDate);
}
