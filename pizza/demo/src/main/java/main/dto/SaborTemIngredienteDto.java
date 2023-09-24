package main.dto;

import lombok.Data;

@Data
public class SaborTemIngredienteDto {
	
	private Long id;

    private SaborDto sabor;
    
    private	IngredienteDto ingrediente;


}
