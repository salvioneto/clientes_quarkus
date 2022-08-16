package br.com.letscode.clientes.config;

import br.com.letscode.clientes.categoria.Categoria;
import br.com.letscode.clientes.categoria.CategoriaMapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.UUID;

public class DBConfig {
    @Inject
    private CategoriaMapper categoriaMapper;

    @PostConstruct
    public void initDB(){

        var categoria1 = new Categoria();
        categoria1.setUuid(UUID.randomUUID().toString());
        categoria1.setName("Programador");
        categoria1.setCode("dev");
        categoria1.persist(categoria1);

        var categoria2 = new Categoria();
        categoria2.setUuid(UUID.randomUUID().toString());
        categoria2.setName("Comerciante");
        categoria2.setCode("sales");
        categoria1.persist(categoria2);
    }
}
