package br.com.letscode.clientes.config;

import br.com.letscode.clientes.bean.AddressBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBean {
    @Bean
    public AddressBean getAddress() {
        return new AddressBean("rua", 100);
    }
}
