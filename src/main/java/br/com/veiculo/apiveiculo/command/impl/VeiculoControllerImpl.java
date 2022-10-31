package br.com.veiculo.apiveiculo.command.impl;

import br.com.veiculo.apiveiculo.command.VeiculoCommand;
import br.com.veiculo.apiveiculo.command.exceptions.ObjectNotFoundException;
import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoControllerImpl implements VeiculoCommand{

    @Autowired
    private VeiculoRepository repository;


    @Override
    public Veiculo findById(Integer id) {
        Optional<Veiculo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado"));
    }
}
