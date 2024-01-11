package com.example.crudapp.repos;

import com.example.crudapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
