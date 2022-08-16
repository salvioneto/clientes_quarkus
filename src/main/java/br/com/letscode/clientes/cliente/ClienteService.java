package br.com.letscode.clientes.cliente;

import org.hibernate.service.spi.InjectService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;
    @Inject
    private ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper){
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> findComTipos() {
        return clienteMapper.toDomainList(
                clienteRepository.findComTipos()
        );
    }
}
