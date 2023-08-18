package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.IngredienteDTO;
import main.entity.Ingrediente;
import main.repository.IngredienteRepository;

@Service
public class IngredienteService {
	@Autowired
	private IngredienteRepository repository;
	
	public List<IngredienteDTO> findAll() {
		
		
		List<Ingrediente> ingredientes = repository.findAll();
		
		List<IngredienteDTO> ingredientesDTO = new ArrayList<>();

		for(Ingrediente ingrediente: ingredientes) {
			IngredienteDTO ingredienteDTO = new IngredienteDTO();
			BeanUtils.copyProperties(ingrediente, ingredienteDTO);
			ingredientesDTO.add(ingredienteDTO);

		}
		
		return ingredientesDTO;
	}
	
	public IngredienteDTO findById(Long id) {
		
		Optional<Ingrediente> ingrediente = repository.findById(id);
		
        Assert.notNull(ingrediente.get(), "Ingrediente não encontrado!");
		
		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		
		BeanUtils.copyProperties(ingrediente.get(), ingredienteDTO);

		return ingredienteDTO;
	}
	
	public IngredienteDTO include(IngredienteDTO ingredienteDTO) {
		
        Assert.isTrue(ingredienteDTO.getNome() != null, "Nome não informado!");
        Assert.isTrue(ingredienteDTO.getValor() != 0, "Valor não informado");
        
		Ingrediente ingrediente = new Ingrediente();
		
		BeanUtils.copyProperties(ingredienteDTO, ingrediente);

		repository.save(ingrediente);
		
		return ingredienteDTO;
	}

	public IngredienteDTO edit(Long id, IngredienteDTO ingredienteDTO) {
	
        Assert.notNull(ingredienteDTO.getNome(), "Nome não informado!");
        Assert.isTrue(ingredienteDTO.getValor() != 0, "Valor não informado");
        
        final Ingrediente ingrediente = this.repository.findById(id).orElse(null);
        
        Assert.notNull(ingrediente, "Ingrediente não encontrado");
        
        ingredienteDTO.setId(id);
		BeanUtils.copyProperties(ingredienteDTO, ingrediente);

		repository.save(ingrediente);
		
		return ingredienteDTO;
	}

	public IngredienteDTO delete(Long id) {
		
		Optional<Ingrediente> ingrediente = repository.findById(id);
		
        Assert.notNull(ingrediente.get(), "Ingrediente não encontrado!");

		IngredienteDTO ingredienteDTO = new IngredienteDTO();
		
		BeanUtils.copyProperties(ingrediente.get(), ingredienteDTO);

		repository.deleteById(id);
		
		return ingredienteDTO;
	}
}
