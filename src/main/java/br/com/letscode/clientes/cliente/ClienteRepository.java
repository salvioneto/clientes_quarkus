package br.com.letscode.clientes.cliente;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
    public List<Cliente> findComTipos() {
        return find("FROM Cliente c JOIN FETCH c.categoria ORDER BY c.nome").list();
    }
}
