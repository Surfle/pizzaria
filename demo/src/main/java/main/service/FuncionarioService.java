package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.FuncionarioDto;
import main.entity.Funcionario;
import main.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;
	
	public List<FuncionarioDto> findAll() {
		
		
		List<Funcionario> funcionarios = repository.findAll();
		
		List<FuncionarioDto> funcionariosDTO = new ArrayList<>();

		for(Funcionario funcionario: funcionarios) {
			FuncionarioDto funcionarioDto = new FuncionarioDto();
			BeanUtils.copyProperties(funcionario, funcionarioDto);
			funcionariosDTO.add(funcionarioDto);

		}
		
		return funcionariosDTO;
	}
	
	public FuncionarioDto findById(Long id) {
		
		Optional<Funcionario> funcionario = repository.findById(id);
		
        Assert.notNull(funcionario.isPresent(), "Funcionario não encontrado!");
		
		FuncionarioDto funcionarioDto = new FuncionarioDto();
		
		if(funcionario.isPresent())
			BeanUtils.copyProperties(funcionario.get(), funcionarioDto);

		return funcionarioDto;
	}
	
	public FuncionarioDto include(FuncionarioDto funcionarioDto) {
		
        Assert.isTrue(funcionarioDto.getNome() != null, "Nome não informado!");
        
		Funcionario funcionario = new Funcionario();
		
		BeanUtils.copyProperties(funcionarioDto, funcionario);

		repository.save(funcionario);
		
		return funcionarioDto;
	}

	public FuncionarioDto edit(Long id, FuncionarioDto funcionarioDto) {
	
        Assert.notNull(funcionarioDto.getNome(), "Nome não informado!");
        
        final Funcionario funcionario = this.repository.findById(id).orElse(null);
        
        Assert.notNull(funcionario, "Funcionario não encontrado");
        
        funcionarioDto.setId(id);
		BeanUtils.copyProperties(funcionarioDto, funcionario);

		repository.save(funcionario);
		
		return funcionarioDto;
	}

	public FuncionarioDto delete(Long id) {
		
		Optional<Funcionario> funcionario = repository.findById(id);
		
        Assert.notNull(funcionario.isPresent(), "Funcionario não encontrado!");

		FuncionarioDto funcionarioDto = new FuncionarioDto();
		
		if(funcionario.isPresent())
			BeanUtils.copyProperties(funcionario.get(), funcionarioDto);

		repository.deleteById(id);
		
		return funcionarioDto;
	}
}
