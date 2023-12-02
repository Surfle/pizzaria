package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.SaborDto;
import main.entity.Sabor;
import main.repository.SaborRepository;

@Service
public class SaborService {
	@Autowired
	private SaborRepository repository;
	
	public List<SaborDto> findAll() {
		
		
		List<Sabor> sabores = repository.findAll();
		
		List<SaborDto> saboresDTO = new ArrayList<>();

		for(Sabor sabor: sabores) {
			SaborDto sabordto = new SaborDto();
			BeanUtils.copyProperties(sabor, sabordto);
			saboresDTO.add(sabordto);

		}
		
		return saboresDTO;
	}
	
	public SaborDto findById(Long id) {
		
		Optional<Sabor> sabor = repository.findById(id);
		
        Assert.notNull(sabor.isPresent(), "Sabor não encontrado!");
		
		SaborDto saborDto = new SaborDto();
		
		if(sabor.isPresent())
			BeanUtils.copyProperties(sabor.get(), saborDto);

		return saborDto;
	}
	
	public SaborDto include(SaborDto saborDto) {
		
        Assert.isTrue(saborDto.getNome() != null, "Nome não informado!");
        Assert.isTrue(saborDto.getValor() != 0, "Valor não informado");
        
		Sabor sabor = new Sabor();
		
		BeanUtils.copyProperties(saborDto, sabor);

		repository.save(sabor);
		
		return saborDto;
	}

	public SaborDto edit(Long id, SaborDto saborDto) {
	
        Assert.notNull(saborDto.getNome(), "Nome não informado!");
        Assert.isTrue(saborDto.getValor() != 0, "Valor não informado");
        
        final Sabor sabor = this.repository.findById(id).orElse(null);
        
        Assert.notNull(sabor, "Sabor não encontrado");
        
        saborDto.setId(id);
		BeanUtils.copyProperties(saborDto, sabor);

		repository.save(sabor);
		
		return saborDto;
	}

	public SaborDto delete(Long id) {
		
		Optional<Sabor> sabor = repository.findById(id);
		
        Assert.notNull(sabor.isPresent(), "Sabor não encontrado!");

		SaborDto saborDto = new SaborDto();
		
		if(sabor.isPresent())
			BeanUtils.copyProperties(sabor.get(), saborDto);

		repository.deleteById(id);
		
		return saborDto;
	}
}
