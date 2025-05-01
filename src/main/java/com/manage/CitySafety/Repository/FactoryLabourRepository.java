package com.manage.CitySafety.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.CitySafety.Entity.FactoryLabour;

@Repository
public interface FactoryLabourRepository extends JpaRepository<FactoryLabour, Long>{

	boolean existsByfactoryLabourName(String factoryLabourName);
}
