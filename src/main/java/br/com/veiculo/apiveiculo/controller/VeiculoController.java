package br.com.veiculo.apiveiculo.controller;

import br.com.veiculo.apiveiculo.command.VeiculoCommand;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<VeiculoDTO> create(@RequestBody VeiculoDTO obj) {
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(command.create(obj).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value ="/{id}")
    public ResponseEntity<VeiculoDTO> update(@PathVariable Integer id, @RequestBody VeiculoDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(command.update(obj), VeiculoDTO.class));
    }
}