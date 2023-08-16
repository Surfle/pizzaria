package main.DTO;

import lombok.Data;

@Data
public class PedidoDTO {
	
	private Long id;
	
	private double valor;
	
	private String observacao;
	
	private boolean entrega;
	
	private FuncionarioDTO funcionario;
	
	private ClienteDTO cliente;

}
