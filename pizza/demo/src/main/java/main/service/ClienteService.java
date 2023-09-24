package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.dto.ClienteDto;
import main.entity.Cliente;
import main.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteDto> findAll() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteDto> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDto clienteDto = new ClienteDto();
            BeanUtils.copyProperties(cliente, clienteDto);
            clientesDTO.add(clienteDto);
        }

        return clientesDTO;
    }

    public ClienteDto findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        Assert.notNull(cliente.isPresent(), "Cliente não encontrado!");

        ClienteDto clienteDto = new ClienteDto();
        if(cliente.isPresent())
        	BeanUtils.copyProperties(cliente.get(), clienteDto);

        return clienteDto;
    }

    public ClienteDto include(ClienteDto clienteDto) {
        Assert.notNull(clienteDto.getNome(), "Nome não informado!");

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);

        repository.save(cliente);

        return clienteDto;
    }

    public ClienteDto edit(Long id, ClienteDto clienteDto) {
        Assert.notNull(clienteDto.getNome(), "Nome não informado!");

        final Cliente cliente = this.repository.findById(id).orElse(null);
        Assert.notNull(cliente, "Cliente não encontrado");

        clienteDto.setId(id);
        BeanUtils.copyProperties(clienteDto, cliente);

        repository.save(cliente);

        return clienteDto;
    }

    public ClienteDto delete(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        Assert.notNull(cliente.isPresent(), "Cliente não encontrado!");

        ClienteDto clienteDto = new ClienteDto();
        if(cliente.isPresent())
        	BeanUtils.copyProperties(cliente.get(), clienteDto);

        repository.deleteById(id);

        return clienteDto;
    }
}
