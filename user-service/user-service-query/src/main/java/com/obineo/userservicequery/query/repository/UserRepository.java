package com.obineo.userservicequery.query.repository;

import com.obineo.userservicequery.query.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
