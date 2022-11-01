package br.com.veiculo.apiveiculo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    @JsonIgnore
    private Integer id;
    private String marca;
    private String modelo;
    private String placaVeiculo;
    private String kmPercorrido;
}
