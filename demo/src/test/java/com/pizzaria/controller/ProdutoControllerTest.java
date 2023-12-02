package com.pizzaria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import main.controller.ProdutoController;
import main.dto.ProdutoDto;
import main.entity.Produto;
import main.service.ProdutoService;

@SpringBootTest
class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {
        List<ProdutoDto> produtosSimulados = new ArrayList<>();
        ProdutoDto produto1 = new ProdutoDto();
        produto1.setId(1L);
        produto1.setNome("Produto 1");
        produtosSimulados.add(produto1);

        when(produtoService.findAll()).thenReturn(produtosSimulados);

        ResponseEntity<List<ProdutoDto>> resultado = produtoController.findAll();

        verify(produtoService).findAll();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(produtosSimulados.size(), resultado.getBody().size());
        assertEquals(produtosSimulados.get(0).getNome(), resultado.getBody().get(0).getNome());
    }

    @Test
    void testFindById() {
        ProdutoDto produtoSimulado = new ProdutoDto();
        produtoSimulado.setId(1L);
        produtoSimulado.setNome("Produto 1");

        when(produtoService.findById(1L)).thenReturn(produtoSimulado);

        ResponseEntity<ProdutoDto> resultado = produtoController.findById(1L);

        verify(produtoService).findById(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(produtoSimulado.getId(), resultado.getBody().getId());
        assertEquals(produtoSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testInclude() {
        ProdutoDto produtoDTOSimulado = new ProdutoDto();
        produtoDTOSimulado.setNome("Produto Novo");
        
        Produto produtoSimulado = new Produto();
        produtoSimulado.setNome("Produto Novo");

        when(produtoService.include(produtoDTOSimulado)).thenReturn(produtoSimulado);

        ResponseEntity<Produto> resultado = produtoController.include(produtoDTOSimulado);

        verify(produtoService).include(produtoDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(produtoDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testEdit() {
        ProdutoDto produtoDTOSimulado = new ProdutoDto();
        produtoDTOSimulado.setId(1L);
        produtoDTOSimulado.setNome("Produto Editado");

        when(produtoService.edit(1L, produtoDTOSimulado)).thenReturn(produtoDTOSimulado);

        ResponseEntity<ProdutoDto> resultado = produtoController.edit(1L, produtoDTOSimulado);

        verify(produtoService).edit(1L, produtoDTOSimulado);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(produtoDTOSimulado.getNome(), resultado.getBody().getNome());
    }

    @Test
    void testDelete() {
        ProdutoDto produtoDTOSimulado = new ProdutoDto();
        produtoDTOSimulado.setId(1L);
        produtoDTOSimulado.setNome("Produto 1");

        when(produtoService.delete(1L)).thenReturn(produtoDTOSimulado);

        ResponseEntity<ProdutoDto> resultado = produtoController.delete(1L);

        verify(produtoService).delete(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(produtoDTOSimulado.getNome(), resultado.getBody().getNome());
    }
}