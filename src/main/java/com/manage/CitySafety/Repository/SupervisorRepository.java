package com.manage.CitySafety.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.Supervisor;

import jakarta.transaction.Transactional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long>{

	Supervisor findByName(String name);
	@Transactional
    @Modifying
    @Query("UPDATE Supervisor s SET s.password = :password WHERE s.name = :name")
    int updateSupervisorPassword(String name, String password);
}
