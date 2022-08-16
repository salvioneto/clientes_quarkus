package br.com.letscode.clientes.bean;

import org.springframework.stereotype.Component;


public class AddressBean {
    private String street;
    private int number;

    public AddressBean(String street, int number) {
        this.street = street;
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
    public AddressBean setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddressBean setStreet(String street) {
        this.street = street;
        return this;
    }
}
