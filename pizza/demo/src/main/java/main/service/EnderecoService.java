package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.EnderecoDTO;
import main.entity.Endereco;
import main.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	
	public List<EnderecoDTO> findAll() {
		
		
		List<Endereco> enderecos = repository.findAll();
		
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();

		for(Endereco endereco: enderecos) {
			EnderecoDTO enderecodto = new EnderecoDTO();
			BeanUtils.copyProperties(endereco, enderecodto);
			enderecosDTO.add(enderecodto);

		}
		
		return enderecosDTO;
	}
	
	public EnderecoDTO findById(Long id) {
		
		Optional<Endereco> endereco = repository.findById(id);
		
        Assert.notNull(endereco.get(), "Endereco não encontrado!");
		
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		BeanUtils.copyProperties(endereco.get(), enderecoDTO);

		return enderecoDTO;
	}
	
	public EnderecoDTO include(EnderecoDTO enderecoDTO) {
		
        Assert.notNull(enderecoDTO.getRua(), "Rua não informada!");
        Assert.isTrue(enderecoDTO.getNumero() != 0, "Número não informado");
        
		Endereco endereco = new Endereco();
		
		BeanUtils.copyProperties(enderecoDTO, endereco);

		repository.save(endereco);
		
		return enderecoDTO;
	}

	public EnderecoDTO edit(Long id, EnderecoDTO enderecoDTO) {
	
        Assert.notNull(enderecoDTO.getRua(), "Rua não informada!");
        Assert.isTrue(enderecoDTO.getNumero() != 0, "Número não informado");
        
        final Endereco endereco = this.repository.findById(id).orElse(null);
        
        Assert.notNull(endereco, "Endereco não encontrado");
        
        enderecoDTO.setId(id);
		BeanUtils.copyProperties(enderecoDTO, endereco);

		repository.save(endereco);
		
		return enderecoDTO;
	}

	public EnderecoDTO delete(Long id) {
		
		Optional<Endereco> endereco = repository.findById(id);
		
        Assert.notNull(endereco.get(), "Endereco não encontrado!");

		EnderecoDTO enderecoDTO = new EnderecoDTO();
		
		BeanUtils.copyProperties(endereco.get(), enderecoDTO);

		repository.deleteById(id);
		
		return enderecoDTO;
	}
}
