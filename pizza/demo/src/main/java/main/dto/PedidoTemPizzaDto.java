package main.dto;

import lombok.Data;

@Data
public class PedidoTemPizzaDto {
	private Long id;

	private PedidoDto pedido;
	
	private PizzaDto pizza;
}
