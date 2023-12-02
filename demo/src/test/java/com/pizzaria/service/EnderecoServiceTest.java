package com.pizzaria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
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

import main.dto.EnderecoDto;
import main.entity.Cliente;
import main.entity.Endereco;
import main.repository.ClienteRepository;
import main.repository.EnderecoRepository;
import main.service.EnderecoService;

@SpringBootTest
class EnderecoServiceTest {

	@InjectMocks
	private EnderecoService enderecoService;

	@Mock
	private EnderecoRepository enderecoRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@BeforeEach
	public void setUp() {
	}

	@Test
	void testFindAll() {

		List<Endereco> enderecosSimulados = new ArrayList<>();
		Endereco endereco1 = new Endereco();
		endereco1.setId(1L);
		endereco1.setRua("Rua 1");
		endereco1.setNumero(123L);
		enderecosSimulados.add(endereco1);

		when(enderecoRepository.findAll()).thenReturn(enderecosSimulados);

		List<EnderecoDto> resultado = enderecoService.findAll();

		verify(enderecoRepository).findAll();

		assertEquals(enderecosSimulados.size(), resultado.size());
		assertEquals(enderecosSimulados.get(0).getRua(), resultado.get(0).getRua());
	}

	@Test
	void testFindById() {

		Endereco enderecoSimulado = new Endereco();
		enderecoSimulado.setId(1L);
		enderecoSimulado.setRua("Rua do Endereco");

		when(enderecoRepository.findById(1L)).thenReturn(Optional.of(enderecoSimulado));

		EnderecoDto resultado = enderecoService.findById(1L);

		verify(enderecoRepository).findById(1L);

		assertEquals(enderecoSimulado.getRua(), resultado.getRua());
	}

	@Test
	void testInclude() {

		Cliente clienteSimulado = new Cliente();
		clienteSimulado.setId(1L);

		EnderecoDto enderecoDTOSimulado = new EnderecoDto();
		enderecoDTOSimulado.setRua("Rua do Endereco");
		enderecoDTOSimulado.setNumero(123L);

		when(clienteRepository.findById(1L)).thenReturn(Optional.of(clienteSimulado));

		EnderecoDto resultado = enderecoService.include(enderecoDTOSimulado);

		verify(clienteRepository).findById(1L);

		verify(enderecoRepository).save(any(Endereco.class));

		assertEquals(enderecoDTOSimulado.getRua(), resultado.getRua());
		assertEquals(enderecoDTOSimulado.getNumero(), resultado.getNumero());
	}

	@Test
	void testEdit() {

		EnderecoDto enderecoDTOSimulado = new EnderecoDto();
		enderecoDTOSimulado.setId(1L);
		enderecoDTOSimulado.setRua("Nova Rua do Endereco");
		enderecoDTOSimulado.setNumero(456L);

		when(enderecoRepository.findById(1L)).thenReturn(Optional.of(new Endereco()));

		EnderecoDto resultado = enderecoService.edit(1L, enderecoDTOSimulado);

		verify(enderecoRepository).findById(1L);

		verify(enderecoRepository).save(any(Endereco.class));

		assertEquals(enderecoDTOSimulado.getRua(), resultado.getRua());
		assertEquals(enderecoDTOSimulado.getNumero(), resultado.getNumero());
	}

	@Test
    void testDelete() {

        when(enderecoRepository.findById(1L)).thenReturn(Optional.of(new Endereco()));

        EnderecoDto resultado = enderecoService.delete(1L);

        verify(enderecoRepository).findById(1L);

        verify(enderecoRepository).deleteById(1L);

        assertNotNull(resultado);
    }
}