package com.microservices.userservice.repository;

import com.microservices.userservice.entity.Address;
import com.microservices.userservice.entity.ApiResponseObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AddressRepository extends JpaRepository<Address, Long> {
}
