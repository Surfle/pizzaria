package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {}
