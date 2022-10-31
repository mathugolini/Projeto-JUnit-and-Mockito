package br.com.veiculo.apiveiculo.controller;

import br.com.veiculo.apiveiculo.command.VeiculoCommand;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private VeiculoCommand command;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VeiculoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(command.findById(id), VeiculoDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAll() {
        return ResponseEntity.ok().body(command.findAll()
                .stream().map(x -> mapper.map(x, VeiculoDTO.class)).collect(Collectors.toList()));
    }
}