package br.com.letscode.clientes.categoria;

import br.com.letscode.clientes.cliente.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Categoria extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uuid;

    private String name;

    private String code;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private Collection<Cliente> clientes = new ArrayList<>();
}
