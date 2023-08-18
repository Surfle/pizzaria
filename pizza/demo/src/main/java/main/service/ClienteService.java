package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.DTO.ClienteDTO;
import main.entity.Cliente;
import main.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = repository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO();
            BeanUtils.copyProperties(cliente, clienteDTO);
            clientesDTO.add(clienteDTO);
        }

        return clientesDTO;
    }

    public ClienteDTO findById(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        Assert.notNull(cliente.get(), "Cliente não encontrado!");

        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente.get(), clienteDTO);

        return clienteDTO;
    }

    public ClienteDTO include(ClienteDTO clienteDTO) {
        Assert.notNull(clienteDTO.getNome(), "Nome não informado!");

        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);

        repository.save(cliente);

        return clienteDTO;
    }

    public ClienteDTO edit(Long id, ClienteDTO clienteDTO) {
        Assert.notNull(clienteDTO.getNome(), "Nome não informado!");

        final Cliente cliente = this.repository.findById(id).orElse(null);
        Assert.notNull(cliente, "Cliente não encontrado");

        clienteDTO.setId(id);
        BeanUtils.copyProperties(clienteDTO, cliente);

        repository.save(cliente);

        return clienteDTO;
    }

    public ClienteDTO delete(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        Assert.notNull(cliente.get(), "Cliente não encontrado!");

        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente.get(), clienteDTO);

        repository.deleteById(id);

        return clienteDTO;
    }
}
