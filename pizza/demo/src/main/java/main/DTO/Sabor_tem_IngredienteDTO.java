package main.DTO;

import lombok.Data;

@Data
public class Sabor_tem_IngredienteDTO {
	
	private Long id;

    private SaborDTO sabor;
    
    private	IngredienteDTO ingrediente;


}
