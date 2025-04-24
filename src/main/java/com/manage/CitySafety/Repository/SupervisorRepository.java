package com.manage.CitySafety.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.Supervisor;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long>{

	Supervisor findByName(String name);
}
