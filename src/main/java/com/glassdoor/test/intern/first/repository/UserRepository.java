package com.glassdoor.test.intern.first.repository;

import com.glassdoor.test.intern.first.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
