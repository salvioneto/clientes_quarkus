package br.com.letscode.clientes.DTO;

import java.util.List;

public class CategoriaDTO {

    private String uuid;
    private String name;
    private String code;
    private List<ClienteDTO> lista;

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
