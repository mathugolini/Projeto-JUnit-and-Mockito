package br.com.veiculo.apiveiculo.config;

import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocaConfig {

    @Autowired
    private VeiculoRepository repository;

    @Bean
    public void startDB() {
        Veiculo v1 = new Veiculo(null, "audi", "a3", "BRA2E19", "10000");
        Veiculo v2 = new Veiculo(null, "ford", "focus", "RIO2A18", "20000");

        repository.saveAll(List.of(v1, v2));
    }

}
