package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {}
