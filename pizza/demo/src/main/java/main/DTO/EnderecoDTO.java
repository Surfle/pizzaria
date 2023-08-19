package main.DTO;

import lombok.Data;
import main.entity.Cliente;

@Data
public class EnderecoDTO {
	private Long id;
	
	private String rua;
	
	private Long numero;
	
	private Cliente cliente;
}
