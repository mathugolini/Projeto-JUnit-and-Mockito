package br.com.veiculo.apiveiculo.repositories;

import br.com.veiculo.apiveiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    Optional<Veiculo> findByPlacaVeiculo(String placaVeiculo);
}
