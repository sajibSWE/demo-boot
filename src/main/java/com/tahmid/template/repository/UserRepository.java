package com.tahmid.template.repository;

import com.tahmid.template.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
