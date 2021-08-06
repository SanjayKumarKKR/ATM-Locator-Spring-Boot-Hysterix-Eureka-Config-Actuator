package com.microservices.userservice.repository;

import com.microservices.userservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
