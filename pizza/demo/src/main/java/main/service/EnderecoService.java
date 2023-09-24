package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.EnderecoDto;
import main.entity.Cliente;
import main.entity.Endereco;
import main.repository.ClienteRepository;
import main.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository repository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<EnderecoDto> findAll() {
		
		
		List<Endereco> enderecos = repository.findAll();
		
		List<EnderecoDto> enderecosDTO = new ArrayList<>();

		for(Endereco endereco: enderecos) {
			EnderecoDto enderecodto = new EnderecoDto();
			BeanUtils.copyProperties(endereco, enderecodto);
			enderecosDTO.add(enderecodto);

		}
		
		return enderecosDTO;
	}
	
	public EnderecoDto findById(Long id) {
		
		Optional<Endereco> endereco = repository.findById(id);
		
        Assert.notNull(endereco.isPresent(), "Endereco não encontrado!");
		
		EnderecoDto enderecoDto = new EnderecoDto();
		
		if(endereco.isPresent())
			BeanUtils.copyProperties(endereco.get(), enderecoDto);

		return enderecoDto;
	}
	
	public EnderecoDto include(Long id, EnderecoDto enderecoDto) {
		Optional<Cliente> cliente = clienteRepository.findById(id);

        Assert.notNull(cliente.isPresent(), "Cliente não informado");
        Assert.notNull(enderecoDto.getRua(), "Rua não informada!");
        Assert.isTrue(enderecoDto.getNumero() != 0, "Número não informado");
        
		Endereco endereco = new Endereco();
		
		BeanUtils.copyProperties(enderecoDto, endereco);
		
		if(cliente.isPresent())
			endereco.setCliente(cliente.get());
		
		repository.save(endereco);
		
		return enderecoDto;
	}

	public EnderecoDto edit(Long id, EnderecoDto enderecoDto) {
	
        Assert.notNull(enderecoDto.getRua(), "Rua não informada!");
        Assert.isTrue(enderecoDto.getNumero() != 0, "Número não informado");
        
        final Endereco endereco = this.repository.findById(id).orElse(null);
        
        Assert.notNull(endereco, "Endereco não encontrado");
        
        enderecoDto.setId(id);
		BeanUtils.copyProperties(enderecoDto, endereco);

		repository.save(endereco);
		
		return enderecoDto;
	}

	public EnderecoDto delete(Long id) {
		
		Optional<Endereco> endereco = repository.findById(id);
		
        Assert.notNull(endereco.isPresent(), "Endereco não encontrado!");

		EnderecoDto enderecoDto = new EnderecoDto();
        
		if(endereco.isPresent())
			BeanUtils.copyProperties(endereco.get(), enderecoDto);

		repository.deleteById(id);
		
		return enderecoDto;
	}
}
