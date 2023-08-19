package main.DTO;

import lombok.Data;
import main.entity.Cliente;
import main.entity.Funcionario;

@Data
public class PedidoDTO {
	
	private Long id;
	
	private double valor;
	
	private String observacao;
	
	private boolean entrega;
	
	private Funcionario funcionario;
	
	private Cliente cliente;

}
