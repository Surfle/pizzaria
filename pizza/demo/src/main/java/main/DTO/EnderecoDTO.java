package main.DTO;

import lombok.Data;

@Data
public class EnderecoDTO {
	private Long id;
	
	private String rua;
	
	private Long numero;
	
	private ClienteDTO cliente;
}
