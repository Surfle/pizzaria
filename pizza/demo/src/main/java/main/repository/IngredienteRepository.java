package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {}
