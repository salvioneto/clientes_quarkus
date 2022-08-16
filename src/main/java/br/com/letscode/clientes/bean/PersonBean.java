package br.com.letscode.clientes.bean;

import org.springframework.stereotype.Component;

public class PersonBean {
    private AddressBean addressBean;

    public PersonBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public PersonBean setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
        return this;
    }
}
