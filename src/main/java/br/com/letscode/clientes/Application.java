package br.com.letscode.clientes;

import br.com.letscode.clientes.bean.CompanyBean;
import br.com.letscode.clientes.model.Categoria;
import br.com.letscode.clientes.model.Cliente;
import br.com.letscode.clientes.repository.CategoriaRepository;
import br.com.letscode.clientes.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

//Enunciado
/*
Crie uma API em Quarkus com a base de dados H2Database em memória que suporte.

GET /client/list
Listar todos os clientes em JSON.

PUT /client/{id}
Alterar os dados do cliente associado ao ID.

POST /client
Criar um novo cliente.

DELETE /client/{id}
Remover do sistema o cliente assosiado ao ID.

O cliente tem os seguintes dados:

Name, mínimo 5 legras e no máximo 100 caractéres
Age, mínimo 18 anos
VAT Number, exemplo XX999999999
Email

Os dados do cliente são obrigatórios e precisam ser validados, não deve permitir inserir ou alterar dados inválidos.*/
/*
Utilizar classe DTO, evitar retornar exceção e utilizar camada de serviço (service).
Cada cliente tem que pertencer a uma Categoria, que deve ter um modelo de dados próprio, contendo o nome e o código.
Ao criar ou editar um cliente tem que permitir alterar a Categoria a partir do código respectivo da Categoria.
Deve existir pelo duas Categorias quando a API iniciar que devem ser pré carregados, como Categoria de Programador e Comerciante.
As categorias devem suportar os mesmos métodos de serviços REST que os clientes, como GET, PUT, POST e DELETE, seguindo a mesma lógica.
*/

@SpringBootApplication
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		CompanyBean company = context.getBean(CompanyBean.class);
		LOGGER.warn("address :: street :: " + company.getAddress().getStreet());
	}

	@Bean
	public CommandLineRunner loadDB(ClienteRepository clienteRepository, CategoriaRepository categoriaRepository) throws Exception {
		return (args) -> {
			LOGGER.info("Carregando a base de dados");

			Categoria catDev = categoriaRepository.findByCode("dev").get(0);
			Categoria catSales = categoriaRepository.findByCode("sales").get(0);

			Cliente cliente1 = new Cliente();
			cliente1.setUuid(UUID.randomUUID().toString());
			cliente1.setName("Axl Rose");
			cliente1.setAge(60);
			cliente1.setVATnumber("AB123456789");
			cliente1.setEmail("axl@gunsnroses.com");
			cliente1.setCategoria(catDev);

			Cliente cliente2 = new Cliente();
			cliente2.setUuid(UUID.randomUUID().toString());
			cliente2.setName("Slash");
			cliente2.setAge(55);
			cliente2.setVATnumber("XY111222333");
			cliente2.setEmail("slash@gunsnroses.com");
			cliente2.setCategoria(catSales);

			Cliente cliente3 = new Cliente();
			cliente3.setUuid(UUID.randomUUID().toString());
			cliente3.setName("Duff McKagan");
			cliente3.setAge(56);
			cliente3.setVATnumber("RR999888777");
			cliente3.setEmail("duff@gunsnroses.com");
			cliente3.setCategoria(catSales);

			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);
			clienteRepository.save(cliente3);

			clienteRepository.findAll().forEach(
					(c) ->
							LOGGER.info("Cliente criado com sucesso: \n"
									+ c.getName()
									+ " - " + c.getEmail() + "\n"
									+ "Categoria: "+ c.getCategoria().getCode()
									+ "\n")
			);

			LOGGER.info("Base carregada");
		};
	}

}