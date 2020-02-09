package com.waheedtechblog.springsecurityjwt.dao;

import com.waheedtechblog.springsecurityjwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // fetch user from DB based on username
    // Read more about JPA from here
    //     https://github.com/abdulwaheed18/spring-boot-tutorial/tree/master/spring-data-jpa
    Optional<User> findByUsername(String userName);
}

