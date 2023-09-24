package main.dto;

import lombok.Data;

@Data
public class PedidoTemProdutoDto {
	private Long id;

	private PedidoDto pedido;
	
	private ProdutoDto produto;
	
}
