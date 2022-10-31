package br.com.veiculo.apiveiculo.command;

import br.com.veiculo.apiveiculo.model.Veiculo;

import java.util.List;

public interface VeiculoCommand {

    Veiculo findById(Integer id);

    List<Veiculo> findAll();

}
