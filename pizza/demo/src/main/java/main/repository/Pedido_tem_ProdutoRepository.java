package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Pedido_tem_Produto;

public interface Pedido_tem_ProdutoRepository extends JpaRepository<Pedido_tem_Produto, Long> {}
