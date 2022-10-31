package br.com.veiculo.apiveiculo.controller;

import br.com.veiculo.apiveiculo.model.Veiculo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/veiculo")
public class VeiculoController {

    @GetMapping(value="/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new Veiculo(1, "audi", "a3", "BRA2E19", "10000"));
    }
}
