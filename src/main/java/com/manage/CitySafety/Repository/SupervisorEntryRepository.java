package com.manage.CitySafety.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.SupervisorEntry;

@Repository
public interface SupervisorEntryRepository extends JpaRepository<SupervisorEntry, Long>{
	
	List<SupervisorEntry> findBySupervisorName(String supervisorName);
	List<SupervisorEntry> findBySupervisorNameAndDate(String supervisorName, LocalDate date);
	List<SupervisorEntry> findBySupervisorNameAndDateBetween(String supervisorName, LocalDate startDate,LocalDate endDate);
	

}
