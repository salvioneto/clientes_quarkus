package br.com.letscode.clientes.cliente;

import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ClienteMapper {
    ClienteDTO toClienteDTO(Cliente cliente);

    Cliente toClienteEntity(ClienteDTO clienteDTO);
}
