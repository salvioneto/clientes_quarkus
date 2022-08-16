package br.com.letscode.clientes.resource;

import br.com.letscode.clientes.model.Cliente;
import br.com.letscode.clientes.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path({"/client"})
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path({"/list"})
    public List<Cliente> getClientes() {
        return Cliente.listAll();
    }

    @PUT
    @Path({"/{id}"})
    public void updateCliente(@Valid @PathParam(value="id") long id, @RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        LOGGER.info("Dados do cliente " + id + " atualizados. :: "+ cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @POST({ "/" })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Cliente createCliente(Cliente cliente) {
        cliente.persist();
        return cliente;
    }

    @DELETE
    @Path({"/{id}"})
    public void deleteCliente(@PathParam(value="id") long id) {
        var cliente = clienteRepository.findById(id);
        clienteRepository.deleteById(id);
        LOGGER.info("Cliente " + id + " removido. :: " + cliente);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
