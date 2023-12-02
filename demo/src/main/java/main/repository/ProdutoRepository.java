package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
