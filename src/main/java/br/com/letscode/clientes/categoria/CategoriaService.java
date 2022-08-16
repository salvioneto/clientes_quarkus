package br.com.letscode.clientes.categoria;

import br.com.letscode.clientes.exceptions.NotFoundException;

import javax.inject.Inject;
import java.util.List;

public class CategoriaService {

    @Inject
    private CategoriaMapper categoriaMapper;

    @Inject
    public CategoriaRepository categoriaRepository;

    public Categoria findCategoriaByUUIDComUsuarios(String uuid) throws NotFoundException {
        List<Categoria> categorias = categoriaMapper.findCategoriaByUUIDComUsuarios(uuid);
        var dbCategoria = categorias.stream().peek(
                (c) -> c.getClientes().forEach((cl) -> cl.setCategoria(null))
        ).findFirst();
        return dbCategoria.orElseThrow(NotFoundException::new);
    };

    public List<Categoria> findCategoriaComClientes() {
        return categoriaMapper.findCategoriaComUsuarios();
    }
}

