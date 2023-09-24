package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.IngredienteDto;
import main.entity.Ingrediente;
import main.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository repository;
	
	public List<IngredienteDto> findAll() {
		
		
		List<Ingrediente> ingredientes = repository.findAll();
		
		List<IngredienteDto> ingredientesDTO = new ArrayList<>();

		for(Ingrediente ingrediente: ingredientes) {
			IngredienteDto ingredienteDto = new IngredienteDto();
			BeanUtils.copyProperties(ingrediente, ingredienteDto);
			ingredientesDTO.add(ingredienteDto);

		}
		
		return ingredientesDTO;
	}
	
	public IngredienteDto findById(Long id) {
		
		Optional<Ingrediente> ingrediente = repository.findById(id);
		
        Assert.notNull(ingrediente.isPresent(), "Ingrediente não encontrado!");
		
		IngredienteDto ingredienteDto = new IngredienteDto();
	
		if(ingrediente.isPresent())
			BeanUtils.copyProperties(ingrediente.get(), ingredienteDto);

		return ingredienteDto;
	}
	
	public IngredienteDto include(IngredienteDto ingredienteDto) {
		
        Assert.isTrue(ingredienteDto.getNome() != null, "Nome não informado!");
        Assert.isTrue(ingredienteDto.getValor() != 0, "Valor não informado");
        
		Ingrediente ingrediente = new Ingrediente();
		
		BeanUtils.copyProperties(ingredienteDto, ingrediente);

		repository.save(ingrediente);
		
		return ingredienteDto;
	}

	public IngredienteDto edit(Long id, IngredienteDto ingredienteDto) {
	
        Assert.notNull(ingredienteDto.getNome(), "Nome não informado!");
        Assert.isTrue(ingredienteDto.getValor() != 0, "Valor não informado");
        
        final Ingrediente ingrediente = this.repository.findById(id).orElse(null);
        
        Assert.notNull(ingrediente, "Ingrediente não encontrado");
        
        ingredienteDto.setId(id);
		BeanUtils.copyProperties(ingredienteDto, ingrediente);

		repository.save(ingrediente);
		
		return ingredienteDto;
	}

	public IngredienteDto delete(Long id) {
		
		Optional<Ingrediente> ingrediente = repository.findById(id);
		
        Assert.notNull(ingrediente.isPresent(), "Ingrediente não encontrado!");

		IngredienteDto ingredienteDto = new IngredienteDto();

		if(ingrediente.isPresent())
			BeanUtils.copyProperties(ingrediente.get(), ingredienteDto);

		repository.deleteById(id);
		
		return ingredienteDto;
	}
}
