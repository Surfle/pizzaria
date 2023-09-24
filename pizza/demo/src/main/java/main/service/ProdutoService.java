package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.ProdutoDto;
import main.entity.Produto;
import main.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	public List<ProdutoDto> findAll() {
		
		
		List<Produto> produtos = repository.findAll();
		
		List<ProdutoDto> produtosDTO = new ArrayList<>();

		for(Produto produto: produtos) {
			ProdutoDto produtoDto = new ProdutoDto();
			BeanUtils.copyProperties(produto, produtoDto);
			produtosDTO.add(produtoDto);

		}
		
		return produtosDTO;
	}
	
	public ProdutoDto findById(Long id) {
		
		Optional<Produto> produto = repository.findById(id);
		
        Assert.notNull(produto.isPresent(), "Produto não encontrado!");
		
		ProdutoDto produtoDto = new ProdutoDto();
		
		if(produto.isPresent())
			BeanUtils.copyProperties(produto.get(), produtoDto);

		return produtoDto;
	}
	
	public ProdutoDto include(ProdutoDto produtoDto) {
		
        Assert.isTrue(produtoDto.getNome() != null, "Nome não informado!");
        Assert.isTrue(produtoDto.getValor() != 0, "Valor não informado");
        
		Produto produto = new Produto();
		
		BeanUtils.copyProperties(produtoDto, produto);

		repository.save(produto);
		
		return produtoDto;
	}

	public ProdutoDto edit(Long id, ProdutoDto produtoDto) {
	
        Assert.notNull(produtoDto.getNome(), "Nome não informado!");
        Assert.isTrue(produtoDto.getValor() != 0, "Valor não informado");
        
        final Produto produto = this.repository.findById(id).orElse(null);
        
        Assert.notNull(produto, "Produto não encontrado");
        
        produtoDto.setId(id);
		BeanUtils.copyProperties(produtoDto, produto);

		repository.save(produto);
		
		return produtoDto;
	}

	public ProdutoDto delete(Long id) {
		
		Optional<Produto> produto = repository.findById(id);
		
        Assert.notNull(produto.isPresent(), "Produto não encontrado!");

		ProdutoDto produtoDto = new ProdutoDto();
		
		if(produto.isPresent())
			BeanUtils.copyProperties(produto.get(), produtoDto);

		repository.deleteById(id);
		
		return produtoDto;
	}
}