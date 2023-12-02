package main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.User;


public interface LoginRepository extends JpaRepository<User, Long>{

	public Optional<User> findByUsername(String login);
	
}
