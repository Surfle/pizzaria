package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.SaborDTO;
import main.entity.Sabor;
import main.repository.SaborRepository;

@Service
public class SaborService {
	@Autowired
	private SaborRepository repository;
	
	public List<SaborDTO> findAll() {
		
		
		List<Sabor> sabores = repository.findAll();
		
		List<SaborDTO> saboresDTO = new ArrayList<>();

		for(Sabor sabor: sabores) {
			SaborDTO sabordto = new SaborDTO();
			BeanUtils.copyProperties(sabor, sabordto);
			saboresDTO.add(sabordto);

		}
		
		return saboresDTO;
	}
	
	public SaborDTO findById(Long id) {
		
		Optional<Sabor> sabor = repository.findById(id);
		
        Assert.notNull(sabor.get(), "Sabor não encontrado!");
		
		SaborDTO saborDTO = new SaborDTO();
		
		BeanUtils.copyProperties(sabor.get(), saborDTO);

		return saborDTO;
	}
	
	public SaborDTO include(SaborDTO saborDTO) {
		
        Assert.isTrue(saborDTO.getNome() != null, "Nome não informado!");
        Assert.isTrue(saborDTO.getValor() != 0, "Valor não informado");
        
		Sabor sabor = new Sabor();
		
		BeanUtils.copyProperties(saborDTO, sabor);

		repository.save(sabor);
		
		return saborDTO;
	}

	public SaborDTO edit(Long id, SaborDTO saborDTO) {
	
        Assert.notNull(saborDTO.getNome(), "Nome não informado!");
        Assert.isTrue(saborDTO.getValor() != 0, "Valor não informado");
        
        final Sabor sabor = this.repository.findById(id).orElse(null);
        
        Assert.notNull(sabor, "Sabor não encontrado");
        
        saborDTO.setId(id);
		BeanUtils.copyProperties(saborDTO, sabor);

		repository.save(sabor);
		
		return saborDTO;
	}

	public SaborDTO delete(Long id) {
		
		Optional<Sabor> sabor = repository.findById(id);
		
        Assert.notNull(sabor.get(), "Sabor não encontrado!");

		SaborDTO saborDTO = new SaborDTO();
		
		BeanUtils.copyProperties(sabor.get(), saborDTO);

		repository.deleteById(id);
		
		return saborDTO;
	}
}
