package br.com.letscode.clientes.cliente;


import br.com.letscode.clientes.categoria.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Cliente extends PanacheEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    public long id;

    @Column(nullable = false)
    public String uuid;

    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "É obrigatório informar o nome.")
    @Size(min = 5, max = 100, message = "O nome deve ter entre 5 e 100 caracteres.")
    public String name;
//    @NotBlank(message = "É obrigatório informar a idade.")
    @NotNull
    @Column(nullable = false)
    @Min(value = 18)
    public int age;
    @Column(nullable = false)
    @NotBlank(message = "É obrigatório informar o VAT.")
    @Pattern(regexp = "^[A-Z]{2}[0-9]{9}$")
    public String VATnumber;
    @Column(nullable = false)
    @NotBlank(message = "É obrigatório informar um endereço de email.")
    @Email(message = "O endereço de email informado é inválido")
    public String email;

    @ManyToOne(fetch = FetchType.EAGER)
    public Categoria categoria;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVATnumber() {
        return VATnumber;
    }

    public void setVATnumber(String VATnumber) {
        this.VATnumber = VATnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente(String name, String email) {
        this.email = email;
        this.name = name;
    }
}
