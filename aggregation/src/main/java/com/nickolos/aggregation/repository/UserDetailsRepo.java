package com.nickolos.aggregation.repository;


import com.nickolos.aggregation.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepo extends JpaRepository<User, String> {

    User findByName (String name);
}
