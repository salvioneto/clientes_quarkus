package br.com.letscode.clientes.categoria;

import br.com.letscode.clientes.cliente.ClienteDTO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class CategoriaDTO {

    public String uuid;
    @NotEmpty(message = "Nome obrigatório")
    @Length(min = 3, message = "mínimo 3")
    public String name;
    public String code;
    public List<ClienteDTO> lista;

    public CategoriaDTO(String uuid, String nome, String code, List<ClienteDTO> lista) {
        this.uuid = uuid;
        this.name = nome;
        this.code = code;
        this.lista = lista;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<ClienteDTO> getLista() {
        return lista;
    }
}
