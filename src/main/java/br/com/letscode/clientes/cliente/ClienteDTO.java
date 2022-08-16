package br.com.letscode.clientes.cliente;

import br.com.letscode.clientes.categoria.CategoriaDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ClienteDTO {

    public String uuid;
    @NotEmpty(message = "Nome é obrigatório.")
    public String name;
    public String email;
    public CategoriaDTO categoria;

    public ClienteDTO(String uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
