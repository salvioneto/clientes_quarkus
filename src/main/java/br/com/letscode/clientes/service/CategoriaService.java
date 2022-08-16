package br.com.letscode.clientes.service;

import br.com.letscode.clientes.exceptions.NotFoundException;
import br.com.letscode.clientes.model.Categoria;
import br.com.letscode.clientes.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findCategoriaByUUIDComUsuarios(String uuid) throws NotFoundException {
        List<Categoria> categorias = categoriaRepository.findCategoriaByUUIDComUsuarios(uuid);
        var dbCategoria = categorias.stream().peek(
                (c) -> c.getClientes().forEach((cl) -> cl.setCategoria(null))
        ).findFirst();
        return dbCategoria.orElseThrow(() -> new NotFoundException());
    };

    public List<Categoria> findCategoriaComClientes() {
        return categoriaRepository.findCategoriaComUsuarios();
    }
}

