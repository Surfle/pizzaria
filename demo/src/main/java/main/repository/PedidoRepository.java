package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}
