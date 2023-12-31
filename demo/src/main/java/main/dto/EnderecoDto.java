package main.dto;

import lombok.Data;
import main.entity.Cliente;

@Data
public class EnderecoDto {
	private Long id;
	
	private String rua;
	
	private Long numero;
	
	private Cliente cliente;
}
