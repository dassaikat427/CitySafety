package com.manage.CitySafety.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.Labour;

@Repository  
public interface LabourRepository extends JpaRepository<Labour, Long>{

}
