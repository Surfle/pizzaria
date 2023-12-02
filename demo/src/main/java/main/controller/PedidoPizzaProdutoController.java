package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import main.dto.PedidoPizzaProdutoDto;
import main.service.PedidoPizzaProdutoService;

@RestController
@RequestMapping("/pedpizzapro")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoPizzaProdutoController {
    
	@Autowired
    private PedidoPizzaProdutoService service;

    @PostMapping()
    public ResponseEntity<PedidoPizzaProdutoDto> include(@RequestBody PedidoPizzaProdutoDto pedidoPizzaProdutoDto) {
        try {
            return ResponseEntity.ok(service.include(pedidoPizzaProdutoDto));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
