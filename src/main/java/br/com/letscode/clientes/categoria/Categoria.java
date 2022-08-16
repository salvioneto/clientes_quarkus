package br.com.letscode.clientes.categoria;

import br.com.letscode.clientes.cliente.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Categoria extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(nullable = false)
    public String uuid;

    @NotNull(message = "Deve ser informado um nome.")
    @NotBlank
    public String name;

    public String code;

    public String getUuid() {
        return uuid;
    }

    public Categoria setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Categoria setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Categoria setCode(String code) {
        this.code = code;
        return this;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public Categoria setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
        return this;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    public Collection<Cliente> clientes = new ArrayList<>();
}
