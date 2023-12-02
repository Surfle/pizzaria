package main.dto;

import lombok.Data;

@Data
public class PizzaTemSaborDto {
	private Long id;

    private PizzaDto pizza;
    
    private SaborDto sabor;

}
