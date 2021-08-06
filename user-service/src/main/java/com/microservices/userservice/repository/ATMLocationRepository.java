package com.microservices.userservice.repository;

import com.microservices.userservice.entity.ATMLocation;
import com.microservices.userservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ATMLocationRepository extends JpaRepository<ATMLocation, Long> {
}
