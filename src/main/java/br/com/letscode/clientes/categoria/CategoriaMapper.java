package br.com.letscode.clientes.categoria;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface CategoriaMapper {
    static List<Categoria> findByCode(String dev) {
        return null;
    }

    CategoriaDTO toDomain(Categoria categoria);

    Categoria toEntity(CategoriaDTO categoriaDTO);

    List<Categoria> findCategoriaByUUIDComUsuarios(String uuid);

    List<Categoria> findCategoriaComUsuarios();
}
