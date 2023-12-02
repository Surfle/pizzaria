package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.PedidoDto;
import main.entity.Pedido;
import main.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	public List<PedidoDto> findAll() {
		
		
		List<Pedido> pedidos = repository.findAll();
		
		List<PedidoDto> pedidosDTO = new ArrayList<>();

		for(Pedido pedido: pedidos) {
			PedidoDto pedidoDto = new PedidoDto();
			BeanUtils.copyProperties(pedido, pedidoDto);
			pedidosDTO.add(pedidoDto);

		}
		
		return pedidosDTO;
	}
	
	public PedidoDto findById(Long id) {
		
		Optional<Pedido> pedido = repository.findById(id);
		
        Assert.notNull(pedido.isPresent(), "Pedido não encontrado!");
		
		PedidoDto pedidoDto = new PedidoDto();

		if(pedido.isPresent())
			BeanUtils.copyProperties(pedido.get(), pedidoDto);

		return pedidoDto;
	}
	
	public Pedido include(PedidoDto pedidoDto) {
		
//        Assert.isTrue(pedidoDto.getValor() != 0, "Valor não informado");
        Assert.isTrue(pedidoDto.getCliente().getId() != 0, "Cliente não informado");
        Assert.isTrue(pedidoDto.getFuncionario().getId() != 0, "Funcionario não informado");
                 
//		pedidoDto.setValor(pedidoDto.getValor()+pedidoDto.getProduto().getValor()+pedidoDto.getSabor().getValor());
        
		Pedido pedido = new Pedido();

		BeanUtils.copyProperties(pedidoDto, pedido);
		
		return repository.save(pedido);

	}

	public PedidoDto edit(Long id, PedidoDto pedidoDto) {
	
        //Assert.isTrue(pedidoDto.getValor() != 0, "Valor não informado");
        
        final Pedido pedido = this.repository.findById(id).orElse(null);
        
        Assert.notNull(pedido, "Pedido não encontrado");
        
        pedidoDto.setId(id);
		BeanUtils.copyProperties(pedidoDto, pedido);

		repository.save(pedido);
		
		return pedidoDto;
	}

	public PedidoDto delete(Long id) {
		
		Optional<Pedido> pedido = repository.findById(id);
		
        Assert.notNull(pedido.isPresent(), "Pedido não encontrado!");

		PedidoDto pedidoDto = new PedidoDto();
		
		if(pedido.isPresent())
			BeanUtils.copyProperties(pedido.get(), pedidoDto);

		repository.deleteById(id);
		
		return pedidoDto;
	}
}
