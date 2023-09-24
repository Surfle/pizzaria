package com.pizzaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import main.dto.ProdutoDto;
import main.entity.Produto;
import main.repository.ProdutoRepository;
import main.service.ProdutoService;

@SpringBootTest
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void testFindAll() {
        List<Produto> produtosSimulados = new ArrayList<>();
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto 1");
        produto1.setValor(50.0);
        produtosSimulados.add(produto1);

        when(produtoRepository.findAll()).thenReturn(produtosSimulados);

        List<ProdutoDto> resultado = produtoService.findAll();

        assertEquals(produtosSimulados.size(), resultado.size());
        assertEquals(produtosSimulados.get(0).getNome(), resultado.get(0).getNome());
    }

    @Test
    void testFindById() {
        Produto produtoSimulado = new Produto();
        produtoSimulado.setId(1L);
        produtoSimulado.setNome("Produto Simulado");
        produtoSimulado.setValor(75.0);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoSimulado));

        ProdutoDto resultado = produtoService.findById(1L);

        assertEquals(produtoSimulado.getNome(), resultado.getNome());
    }

    @Test
    void testInclude() {
        ProdutoDto produtoDTOSimulado = new ProdutoDto();
        produtoDTOSimulado.setNome("Produto de Teste");
        produtoDTOSimulado.setValor(80.0);

        ProdutoDto resultado = produtoService.include(produtoDTOSimulado);

        when(produtoRepository.save(any(Produto.class))).thenReturn(new Produto());

        assertEquals(produtoDTOSimulado.getNome(), resultado.getNome());
        assertEquals(produtoDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testEdit() {
        ProdutoDto produtoDTOSimulado = new ProdutoDto();
        produtoDTOSimulado.setId(1L);
        produtoDTOSimulado.setNome("Produto Editado");
        produtoDTOSimulado.setValor(90.0);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto()));
        when(produtoRepository.save(any(Produto.class))).thenReturn(new Produto());

        ProdutoDto resultado = produtoService.edit(1L, produtoDTOSimulado);

        assertEquals(produtoDTOSimulado.getNome(), resultado.getNome());
        assertEquals(produtoDTOSimulado.getValor(), resultado.getValor());
    }

    @Test
    void testDelete() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto()));

        ProdutoDto resultado = produtoService.delete(1L);

        assertNotNull(resultado);
    }
}