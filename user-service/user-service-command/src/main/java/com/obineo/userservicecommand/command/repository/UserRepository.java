package com.obineo.userservicecommand.command.repository;

import com.obineo.userservicecommand.command.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
