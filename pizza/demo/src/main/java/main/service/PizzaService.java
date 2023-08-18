package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.PizzaDTO;
import main.entity.Pizza;
import main.repository.PizzaRepository;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository repository;
	
	public List<PizzaDTO> findAll() {
		
		
		List<Pizza> pizzas = repository.findAll();
		
		List<PizzaDTO> pizzasDTO = new ArrayList<>();

		for(Pizza pizza: pizzas) {
			PizzaDTO pizzaDTO = new PizzaDTO();
			BeanUtils.copyProperties(pizza, pizzaDTO);
			pizzasDTO.add(pizzaDTO);

		}
		
		return pizzasDTO;
	}
	
	public PizzaDTO findById(Long id) {
		
		Optional<Pizza> pizza = repository.findById(id);
		
        Assert.notNull(pizza.get(), "Pizza não encontrada!");
		
		PizzaDTO pizzaDTO = new PizzaDTO();
		
		BeanUtils.copyProperties(pizza.get(), pizzaDTO);

		return pizzaDTO;
	}
	
	public PizzaDTO include(PizzaDTO pizzaDTO) {
		
        Assert.isTrue(pizzaDTO.getTamanho() != 0, "Valor não informado");
        
		Pizza pizza = new Pizza();
		
		BeanUtils.copyProperties(pizzaDTO, pizza);

		repository.save(pizza);
		
		return pizzaDTO;
	}

	public PizzaDTO edit(Long id, PizzaDTO pizzaDTO) {
	
        Assert.isTrue(pizzaDTO.getTamanho() != 0, "Valor não informado");
        
        final Pizza pizza = this.repository.findById(id).orElse(null);
        
        Assert.notNull(pizza, "Pizza não encontrada");
        
        pizzaDTO.setId(id);
		BeanUtils.copyProperties(pizzaDTO, pizza);

		repository.save(pizza);
		
		return pizzaDTO;
	}

	public PizzaDTO delete(Long id) {
		
		Optional<Pizza> pizza = repository.findById(id);
		
        Assert.notNull(pizza.get(), "Pizza não encontrada!");

		PizzaDTO pizzaDTO = new PizzaDTO();
		
		BeanUtils.copyProperties(pizza.get(), pizzaDTO);

		repository.deleteById(id);
		
		return pizzaDTO;
	}
}
