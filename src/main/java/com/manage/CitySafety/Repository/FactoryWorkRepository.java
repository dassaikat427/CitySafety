package com.manage.CitySafety.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manage.CitySafety.Entity.FactoryWork;

public interface FactoryWorkRepository extends JpaRepository<FactoryWork, Long> {
	List<FactoryWork> findByDate(LocalDate date);

}
