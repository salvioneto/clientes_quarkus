package br.com.letscode.clientes.cliente;

import org.mapstruct.Mapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "cdi")
@ApplicationScoped
public interface ClienteMapper {
    ClienteDTO toClienteDTO(Cliente cliente);

    Cliente toClienteEntity(ClienteDTO clienteDTO);

    default List<ClienteDTO> toDomainList(List<Cliente> list) {
        return list.stream()
                .map((c) -> {
                    ClienteDTO cDTO = toClienteDTO(c);
                    return cDTO;
                })
                .collect(Collectors.toList());
    }
}
