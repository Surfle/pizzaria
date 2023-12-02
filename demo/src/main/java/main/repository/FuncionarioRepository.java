package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {}
