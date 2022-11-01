package br.com.veiculo.apiveiculo.command.impl;

import br.com.veiculo.apiveiculo.command.VeiculoCommand;
import br.com.veiculo.apiveiculo.command.exceptions.ObjectNotFoundException;
import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;
import br.com.veiculo.apiveiculo.repositories.VeiculoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoControllerImpl implements VeiculoCommand{

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Veiculo findById(Integer id) {
        Optional<Veiculo> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Veiculo não encontrado"));
    }

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Veiculo create(VeiculoDTO obj) {
        return repository.save(mapper.map(obj, Veiculo.class));
    }
}
