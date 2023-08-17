package main.DTO;

import lombok.Data;

@Data
public class Pizza_tem_SabotDTO {
	private Long id;

    private PizzaDTO pizza;
    
    private SaborDTO sabor;

}
