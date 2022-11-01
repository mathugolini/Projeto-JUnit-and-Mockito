package br.com.veiculo.apiveiculo.command;

import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;

import java.util.List;

public interface VeiculoCommand {

    Veiculo findById(Integer id);

    List<Veiculo> findAll();

    Veiculo create(VeiculoDTO obj);

    Veiculo update(VeiculoDTO obj);

    void delete(Integer id);

}
