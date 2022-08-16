package br.com.letscode.clientes.config;

import br.com.letscode.clientes.model.Categoria;
import br.com.letscode.clientes.repository.CategoriaRepository;
import org.apache.catalina.startup.CatalinaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
public class DBConfig {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostConstruct
    public void initDB(){

        var categoria1 = new Categoria();
        categoria1.setUuid(UUID.randomUUID().toString());
        categoria1.setName("Programador");
        categoria1.setCode("dev");
        categoriaRepository.save(categoria1);

        var categoria2 = new Categoria();
        categoria2.setUuid(UUID.randomUUID().toString());
        categoria2.setName("Comerciante");
        categoria2.setCode("sales");
        categoriaRepository.save(categoria2);
    }
}
