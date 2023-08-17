package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {}