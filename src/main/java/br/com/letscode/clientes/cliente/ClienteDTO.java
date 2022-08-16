package br.com.letscode.clientes.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    public String uuid;
    public String name;
    public String email;

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
