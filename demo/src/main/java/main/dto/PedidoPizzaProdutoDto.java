package main.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoPizzaProdutoDto {
	private long id;
	
	private PedidoDto pedido;

	private List<ProdutoDto> produtos;
	
	private List<PizzaDto> pizzas;
}
