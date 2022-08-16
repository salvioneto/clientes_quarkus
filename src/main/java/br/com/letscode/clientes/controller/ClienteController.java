package br.com.letscode.clientes.controller;

import br.com.letscode.clientes.model.Cliente;
import br.com.letscode.clientes.repository.ClienteRepository;
import br.com.letscode.clientes.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
@RequestMapping ({"/client"})
@RestController
public class ClienteController {

    private final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping({"/list"})
    public ResponseEntity<Iterable<Cliente>> getClientes() {
        LOGGER.info("Lista de clientes");
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateCliente(@Valid @PathVariable(value="id") long id, @RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        LOGGER.info("Dados do cliente " + id + " atualizados. :: "+ cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping({ "/" })
    public ResponseEntity<Void> createCliente(@Valid @RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        LOGGER.info("Novo cliente adicionado. :: "+ cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCliente(@PathVariable(value="id") long id) {
        var cliente = clienteRepository.findById(id);
        clienteRepository.deleteById(id);
        LOGGER.info("Cliente " + id + " removido. :: " + cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
