package com.Elrearning.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Elrearning.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);


Boolean existsByUsername (String username) ;
Boolean existsByEmail (String email) ;
List<User> findAllByUsertype (String usertype );
}
