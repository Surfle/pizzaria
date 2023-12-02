package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.PedidoPizzaProdutoDto;
import main.dto.PizzaDto;
import main.dto.ProdutoDto;
import main.entity.Pedido;
import main.entity.PedidoPizzaProduto;
import main.entity.Pizza;
import main.entity.Produto;
import main.repository.PedidoPizzaProdutoRepository;

@Service
public class PedidoPizzaProdutoService {
  
	@Autowired
    private PedidoPizzaProdutoRepository repository;

	@Autowired
    private PedidoService pedidoService;
	
	@Autowired
    private PizzaService pizzaService;
    
	@Autowired
    private ProdutoService produtoService;
    
    public PedidoPizzaProdutoDto include(PedidoPizzaProdutoDto pedidoPizzaProdutoDto) {
        Assert.notNull(pedidoPizzaProdutoDto.getPedido(), "Pedido não informado!");
    	
    	Pedido newPedido = pedidoService.include(pedidoPizzaProdutoDto.getPedido());
//    	BeanUtils.copyProperties(pedidoPizzaProdutoDto.getPedido(), newPedido);
    	
    	

    	for (PizzaDto pizzaDto: pedidoPizzaProdutoDto.getPizzas()) {
    		
    		Pizza pizza = pizzaService.include(pizzaDto);
    		
    		PedidoPizzaProduto pedidoPizzaProduto = new PedidoPizzaProduto();
    		pedidoPizzaProduto.setPedido(newPedido);
    		pedidoPizzaProduto.setPizza(pizza);
    		
            repository.save(pedidoPizzaProduto);
    	}

    	for (ProdutoDto produtoDto: pedidoPizzaProdutoDto.getProdutos()) {
    		Produto produto = produtoService.include(produtoDto);
    		PedidoPizzaProduto pedidoPizzaProduto = new PedidoPizzaProduto();

    		pedidoPizzaProduto.setPedido(newPedido);
    		pedidoPizzaProduto.setProduto(produto);
    		
            repository.save(pedidoPizzaProduto);
    	}

        return pedidoPizzaProdutoDto;
    }

}
