package br.com.letscode.clientes.bean;

import org.springframework.stereotype.Component;
@Component
public class CompanyBean {
    private AddressBean address;

    public CompanyBean(AddressBean address) {
        this.address = address;
    }

    public AddressBean getAddress() {
        return address;
    }

    public CompanyBean setAddress(AddressBean address) {
        this.address = address;
        return this;
    }
}
