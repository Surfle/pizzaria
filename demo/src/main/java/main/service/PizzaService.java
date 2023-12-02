package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.PizzaDto;
import main.entity.Pizza;
import main.repository.PizzaRepository;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository repository;
	
	public List<PizzaDto> findAll() {
		
		
		List<Pizza> pizzas = repository.findAll();
		
		List<PizzaDto> pizzasDTO = new ArrayList<>();

		for(Pizza pizza: pizzas) {
			PizzaDto pizzaDto = new PizzaDto();
			BeanUtils.copyProperties(pizza, pizzaDto);
			pizzasDTO.add(pizzaDto);

		}
		
		return pizzasDTO;
	}
	
	public PizzaDto findById(Long id) {
		
		Optional<Pizza> pizza = repository.findById(id);
		
        Assert.notNull(pizza.isPresent(), "Pizza não encontrada!");
		
		PizzaDto pizzaDto = new PizzaDto();
		if(pizza.isPresent())
			BeanUtils.copyProperties(pizza.get(), pizzaDto);

		return pizzaDto;
	}
	
	public Pizza include(PizzaDto pizzaDto) {
		
        Assert.isTrue(pizzaDto.getTamanho() != 0, "Valor não informado");
        
		Pizza pizza = new Pizza();
		
		BeanUtils.copyProperties(pizzaDto, pizza);

		
		
		return repository.save(pizza);
	}

	public PizzaDto edit(Long id, PizzaDto pizzaDto) {
	
        Assert.isTrue(pizzaDto.getTamanho() != 0, "Tamanho não informado");
        
        final Pizza pizza = this.repository.findById(id).orElse(null);
        
        Assert.notNull(pizza, "Pizza não encontrada");
        
        pizzaDto.setId(id);
		BeanUtils.copyProperties(pizzaDto, pizza);

		repository.save(pizza);
		
		return pizzaDto;
	}

	public PizzaDto delete(Long id) {
		
		Optional<Pizza> pizza = repository.findById(id);
		
        Assert.notNull(pizza.isPresent(), "Pizza não encontrada!");

		PizzaDto pizzaDto = new PizzaDto();
		if(pizza.isPresent())
			BeanUtils.copyProperties(pizza.get(), pizzaDto);

		repository.deleteById(id);
		
		return pizzaDto;
	}
}
