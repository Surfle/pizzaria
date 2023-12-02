package main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import main.entity.Pedido;
import main.entity.PedidoPizzaProduto;

public interface PedidoPizzaProdutoRepository extends JpaRepository<PedidoPizzaProduto, Long> {
	@Query("SELECT p.pedido FROM PedidoPizzaProduto p WHERE p.pizza.id = :pizzaId")
    List<Pedido> findPedidoByPizzaId(@Param("pizzaId") Long pizzaId);
	
	@Query("SELECT p.pedido FROM PedidoPizzaProduto p WHERE p.produto.id = :produtoId")
    List<Pedido> findPedidoByProdutoId(@Param("produtoId") Long produtoId);
	
}
