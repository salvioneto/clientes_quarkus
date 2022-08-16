package br.com.letscode.clientes.controller;

import br.com.letscode.clientes.DTO.CategoriaDTO;
import br.com.letscode.clientes.DTO.ClienteDTO;
import br.com.letscode.clientes.exceptions.NotFoundException;
import br.com.letscode.clientes.model.Categoria;
import br.com.letscode.clientes.repository.CategoriaRepository;
import br.com.letscode.clientes.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoriaController.class);
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping({"/list"})
    public ResponseEntity<Iterable<CategoriaDTO>> getCategorias() {
        LOGGER.info("Lista de categorias");
        return ResponseEntity.ok(categoriaRepository.findAll()
                .stream().map((c) ->
                        new CategoriaDTO(c.getUuid(), c.getName(), c.getCode(),null))
                .collect(Collectors.toList()));
    }
    @GetMapping({"/list-clients"})
    public ResponseEntity<Iterable<CategoriaDTO>> getClientesPorCategorias() {
        LOGGER.info("Lista de categorias");
        return ResponseEntity.ok(
                categoriaService.findCategoriaComClientes()
                .stream().map((ct) -> new CategoriaDTO(
                        ct.getUuid(),
                        ct.getName(),
                        ct.getCode(),
                        ct.getClientes().stream().map((cl) -> new ClienteDTO(
                                        cl.getUuid(), cl.getName(), cl.getEmail()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList()));
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<CategoriaDTO> findCategoriaByUUDIComUsuarios(@PathVariable(value="uuid") String uuid) {
        try {
            return ResponseEntity.ok(entityToDTO(categoriaService.findCategoriaByUUIDComUsuarios(uuid)));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping({"/{id}"})
    public ResponseEntity<Void> updateCategoria(@Valid @PathVariable(value="id") long id, @RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        LOGGER.info("Dados da categoria " + id + " atualizados. :: "+ categoria);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PostMapping({ "/" })
    public ResponseEntity<Void> createCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaRepository.save(categoria);
        LOGGER.info("Nova categoria adicionada. :: "+ categoria);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCategoria(@PathVariable(value="id") long id) {
        var categoria = categoriaRepository.findById(id);
        categoriaRepository.deleteById(id);
        LOGGER.info("Categoria " + id + " removida. :: " + categoria);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public CategoriaDTO entityToDTO(Optional<Categoria> categoria) {
        if (categoria.isPresent()) {
            return entityToDTO(categoria.get());
        }
        return null;
    }

    public CategoriaDTO entityToDTO(Categoria ct) {
         return new CategoriaDTO(
                    ct.getUuid(),
                    ct.getName(),
                    ct.getCode(),
                    ct.getClientes().stream().map(
                            cl -> new ClienteDTO(
                                    cl.getUuid(), cl.getName(), cl.getEmail())
                    ).collect(Collectors.toList())
         );
    }
}

