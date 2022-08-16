package br.com.letscode.clientes;

import br.com.letscode.clientes.categoria.CategoriaDTO;
import br.com.letscode.clientes.categoria.CategoriaMapper;
import br.com.letscode.clientes.categoria.Categoria;
import br.com.letscode.clientes.cliente.Cliente;
import br.com.letscode.clientes.cliente.ClienteDTO;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.stream.Collectors;

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

@QuarkusMain
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		Quarkus.run(args);

		LOGGER.info("Carregando a base de dados");

		Categoria catDev = CategoriaMapper.findByCode("dev").get(0);
		Categoria catSales = CategoriaMapper.findByCode("sales").get(0);

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

		cliente1.persist();
		cliente2.persist();
		cliente3.persist();

		LOGGER.info("Base carregada");
	};
}
