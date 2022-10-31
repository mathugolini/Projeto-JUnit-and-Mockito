package br.com.veiculo.apiveiculo.repositories;

import br.com.veiculo.apiveiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
