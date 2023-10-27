package main.dto;

import lombok.Data;
import main.entity.Cliente;
import main.entity.Funcionario;
import main.entity.Pizza;
import main.entity.Produto;
import main.entity.Sabor;

@Data
public class PedidoDto {
	
	private Long id;
	
	private double valor;
	
	private String observacao;
	
	private boolean entrega;
	
	private Funcionario funcionario;
	
	private Cliente cliente;
	
	private Sabor sabor;

	private Produto produto;
}
