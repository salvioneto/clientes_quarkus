package br.com.letscode.clientes.DTO;

public class ClienteDTO {

    private String uuid;
    private String name;
    private String email;

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
