package br.com.letscode.clientes.cliente;

import br.com.letscode.clientes.categoria.CategoriaResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
@Path("/client")
public class ClienteResource {
    private final Logger LOGGER = LoggerFactory.getLogger(ClienteResource.class);
    @Inject
    public ClienteMapper clienteMapper;

    @Inject
    public ClienteRepository clienteRepository;
    private ClienteService clienteService;

    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    };

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteDTO> getClientes() {
        LOGGER.info("Lista de clientes");
        return clienteMapper.toDomainList(Cliente.listAll());
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public void updateCliente(@Valid @PathParam(value="id") long id, Cliente cliente) {
        cliente.persist();
        LOGGER.info("Dados do cliente " + id + " atualizados. :: "+ cliente);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ClienteDTO createCliente(@Valid ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toClienteEntity(clienteDTO);
        cliente.persist();
        return clienteMapper.toClienteDTO(cliente);
    }

    @DELETE
    @Path("/{id}")
    public void deleteCliente(@PathParam(value="id") long id) {
        var cliente = clienteRepository.findById(id);
        clienteRepository.deleteById(id);
        LOGGER.info("Cliente " + id + " removido. :: " + cliente);
    }
}
