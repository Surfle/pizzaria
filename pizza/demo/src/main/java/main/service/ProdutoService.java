package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.ProdutoDTO;
import main.entity.Produto;
import main.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	public List<ProdutoDTO> findAll() {
		
		
		List<Produto> produtos = repository.findAll();
		
		List<ProdutoDTO> produtosDTO = new ArrayList<>();

		for(Produto produto: produtos) {
			ProdutoDTO produtoDTO = new ProdutoDTO();
			BeanUtils.copyProperties(produto, produtoDTO);
			produtosDTO.add(produtoDTO);

		}
		
		return produtosDTO;
	}
	
	public ProdutoDTO findById(Long id) {
		
		Optional<Produto> produto = repository.findById(id);
		
        Assert.notNull(produto.get(), "Produto não encontrado!");
		
		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		BeanUtils.copyProperties(produto.get(), produtoDTO);

		return produtoDTO;
	}
	
	public ProdutoDTO include(ProdutoDTO produtoDTO) {
		
        Assert.isTrue(produtoDTO.getNome() != null, "Nome não informado!");
        Assert.isTrue(produtoDTO.getValor() != 0, "Valor não informado");
        
		Produto produto = new Produto();
		
		BeanUtils.copyProperties(produtoDTO, produto);

		repository.save(produto);
		
		return produtoDTO;
	}

	public ProdutoDTO edit(Long id, ProdutoDTO produtoDTO) {
	
        Assert.notNull(produtoDTO.getNome(), "Nome não informado!");
        Assert.isTrue(produtoDTO.getValor() != 0, "Valor não informado");
        
        final Produto produto = this.repository.findById(id).orElse(null);
        
        Assert.notNull(produto, "Produto não encontrado");
        
        produtoDTO.setId(id);
		BeanUtils.copyProperties(produtoDTO, produto);

		repository.save(produto);
		
		return produtoDTO;
	}

	public ProdutoDTO delete(Long id) {
		
		Optional<Produto> produto = repository.findById(id);
		
        Assert.notNull(produto.get(), "Produto não encontrado!");

		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		BeanUtils.copyProperties(produto.get(), produtoDTO);

		repository.deleteById(id);
		
		return produtoDTO;
	}
}