package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.FuncionarioDTO;
import main.entity.Funcionario;
import main.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;
	
	public List<FuncionarioDTO> findAll() {
		
		
		List<Funcionario> funcionarios = repository.findAll();
		
		List<FuncionarioDTO> funcionariosDTO = new ArrayList<>();

		for(Funcionario funcionario: funcionarios) {
			FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
			BeanUtils.copyProperties(funcionario, funcionarioDTO);
			funcionariosDTO.add(funcionarioDTO);

		}
		
		return funcionariosDTO;
	}
	
	public FuncionarioDTO findById(Long id) {
		
		Optional<Funcionario> funcionario = repository.findById(id);
		
        Assert.notNull(funcionario.get(), "Funcionario não encontrado!");
		
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		
		BeanUtils.copyProperties(funcionario.get(), funcionarioDTO);

		return funcionarioDTO;
	}
	
	public FuncionarioDTO include(FuncionarioDTO funcionarioDTO) {
		
        Assert.isTrue(funcionarioDTO.getNome() != null, "Nome não informado!");
        
		Funcionario funcionario = new Funcionario();
		
		BeanUtils.copyProperties(funcionarioDTO, funcionario);

		repository.save(funcionario);
		
		return funcionarioDTO;
	}

	public FuncionarioDTO edit(Long id, FuncionarioDTO funcionarioDTO) {
	
        Assert.notNull(funcionarioDTO.getNome(), "Nome não informado!");
        
        final Funcionario funcionario = this.repository.findById(id).orElse(null);
        
        Assert.notNull(funcionario, "Funcionario não encontrado");
        
        funcionarioDTO.setId(id);
		BeanUtils.copyProperties(funcionarioDTO, funcionario);

		repository.save(funcionario);
		
		return funcionarioDTO;
	}

	public FuncionarioDTO delete(Long id) {
		
		Optional<Funcionario> funcionario = repository.findById(id);
		
        Assert.notNull(funcionario.get(), "Funcionario não encontrado!");

		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		
		BeanUtils.copyProperties(funcionario.get(), funcionarioDTO);

		repository.deleteById(id);
		
		return funcionarioDTO;
	}
}
