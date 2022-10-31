package br.com.veiculo.apiveiculo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    private Integer id;
    private String marca;
    private String modelo;
    private String placaVeiculo;
    private String kmPercorrido;
}
