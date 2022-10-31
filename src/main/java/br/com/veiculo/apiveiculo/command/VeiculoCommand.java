package br.com.veiculo.apiveiculo.command;

import br.com.veiculo.apiveiculo.model.Veiculo;

public interface VeiculoCommand {

    Veiculo findById(Integer id);

}
