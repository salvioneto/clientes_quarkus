package br.com.letscode.clientes.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uuid;

    private String name;

    private String code;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria")
    private Collection<Cliente> clientes = new ArrayList<>();
}
