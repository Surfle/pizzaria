package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.PedidoTemProduto;

public interface PedidoTemProdutoRepository extends JpaRepository<PedidoTemProduto, Long> {}
