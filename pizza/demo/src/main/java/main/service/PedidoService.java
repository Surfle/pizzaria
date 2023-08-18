package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.PedidoDTO;
import main.entity.Pedido;
import main.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;
	
	public List<PedidoDTO> findAll() {
		
		
		List<Pedido> pedidos = repository.findAll();
		
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for(Pedido pedido: pedidos) {
			PedidoDTO pedidoDTO = new PedidoDTO();
			BeanUtils.copyProperties(pedido, pedidoDTO);
			pedidosDTO.add(pedidoDTO);

		}
		
		return pedidosDTO;
	}
	
	public PedidoDTO findById(Long id) {
		
		Optional<Pedido> pedido = repository.findById(id);
		
        Assert.notNull(pedido.get(), "Pedido não encontrado!");
		
		PedidoDTO pedidoDTO = new PedidoDTO();
		
		BeanUtils.copyProperties(pedido.get(), pedidoDTO);

		return pedidoDTO;
	}
	
	public PedidoDTO include(PedidoDTO pedidoDTO) {
		
        Assert.isTrue(pedidoDTO.getValor() != 0, "Valor não informado");
        
		Pedido pedido = new Pedido();
		
		BeanUtils.copyProperties(pedidoDTO, pedido);

		repository.save(pedido);
		
		return pedidoDTO;
	}

	public PedidoDTO edit(Long id, PedidoDTO pedidoDTO) {
	
        Assert.isTrue(pedidoDTO.getValor() != 0, "Valor não informado");
        
        final Pedido pedido = this.repository.findById(id).orElse(null);
        
        Assert.notNull(pedido, "Pedido não encontrado");
        
        pedidoDTO.setId(id);
		BeanUtils.copyProperties(pedidoDTO, pedido);

		repository.save(pedido);
		
		return pedidoDTO;
	}

	public PedidoDTO delete(Long id) {
		
		Optional<Pedido> pedido = repository.findById(id);
		
        Assert.notNull(pedido.get(), "Pedido não encontrado!");

		PedidoDTO pedidoDTO = new PedidoDTO();
		
		BeanUtils.copyProperties(pedido.get(), pedidoDTO);

		repository.deleteById(id);
		
		return pedidoDTO;
	}
}
