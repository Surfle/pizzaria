package main.dto;

import lombok.Data;
import main.entity.Cliente;
import main.entity.Funcionario;

@Data
public class PedidoDto {
	
	private Long id;
	
	private double valor;
	
	private String observacao;
	
	private boolean entrega;
	
	private Cliente cliente;
	
	private Funcionario funcionario;
}
