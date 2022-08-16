package br.com.letscode.clientes.categoria;

import br.com.letscode.clientes.cliente.ClienteDTO;
import br.com.letscode.clientes.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Path("/categoria")
public class CategoriaResource {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoriaResource.class);
    @Inject
    public CategoriaMapper categoriaMapper;

    @Inject
    public CategoriaRepository categoriaRepository;
    @Inject
    public CategoriaService categoriaService;

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> getCategorias() {
        LOGGER.info("Lista de categorias");
        return Categoria.listAll();
    }
    @GET
    @Path("/list-clients")
    public List<CategoriaDTO> getClientesPorCategorias() {
        LOGGER.info("Lista de categorias");
        return categoriaService.findCategoriaComClientes()
                .stream().map((ct) -> new CategoriaDTO(
                        ct.getUuid(),
                        ct.getName(),
                        ct.getCode(),
                        ct.getClientes().stream().map((cl) -> new ClienteDTO(
                                        cl.getUuid(), cl.getName(), cl.getEmail()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
    @GET
    @Path("/{uuid}")
    public CategoriaDTO findCategoriaByUUDIComUsuarios(@PathParam (value="uuid") String uuid) {
        try {
            return entityToDTO(categoriaService.findCategoriaByUUIDComUsuarios(uuid));
        } catch (NotFoundException e) {
            return null;
        }
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCategoria(@Valid @PathParam(value="id") long id, Categoria categoria) {
        categoria.persist();
        LOGGER.info("Dados da categoria " + id + " atualizados. :: "+ categoria);

    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public void createCategoria(@Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria.persist();
        LOGGER.info("Nova categoria adicionada. :: "+ categoria);
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteCategoria(@PathParam(value="id") long id) {
        var categoria = categoriaRepository.findById(id);
        categoriaRepository.deleteById(id);
        LOGGER.info("Categoria " + id + " removida. :: " + categoria);
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

