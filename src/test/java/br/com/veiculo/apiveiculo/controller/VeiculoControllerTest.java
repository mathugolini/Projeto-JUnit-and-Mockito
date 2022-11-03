package br.com.veiculo.apiveiculo.controller;


import br.com.veiculo.apiveiculo.command.impl.VeiculoCommandImpl;
import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@SpringBootTest
class VeiculoControllerTest {

    public static final int ID = 1;
    public static final String MARCA = "bmw";
    public static final String MODELO = "320i";
    public static final String PLACA_VEICULO = "BRB2E19";
    public static final String KM_PERCORRIDO = "30000";

    public static final Integer INDEX = 0;

    private Veiculo veiculo;
    private VeiculoDTO veiculoDTO;

    @InjectMocks
    private VeiculoController controller;

    @Mock
    private VeiculoCommandImpl command;

    @Mock
    private ModelMapper mapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVeiculo();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(command.findById(anyInt())).thenReturn(veiculo);
        when(mapper.map(any(), any())).thenReturn(veiculoDTO);

       ResponseEntity<VeiculoDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(VeiculoDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(MARCA, response.getBody().getMarca());
        assertEquals(MODELO, response.getBody().getModelo());
        assertEquals(PLACA_VEICULO, response.getBody().getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getBody().getKmPercorrido());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO() {
        when(command.findAll()).thenReturn(List.of(veiculo));
        when(mapper.map(any(), any())).thenReturn(veiculoDTO);

        ResponseEntity<List<VeiculoDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(VeiculoDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(MARCA, response.getBody().get(INDEX).getMarca());
        assertEquals(MODELO, response.getBody().get(INDEX).getModelo());
        assertEquals(PLACA_VEICULO, response.getBody().get(INDEX).getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getBody().get(INDEX).getKmPercorrido());
    }

    @Test
    void whenCreateThenReturnCreated() {
        when(command.create(any())).thenReturn(veiculo);

        ResponseEntity<VeiculoDTO> response = controller.create(veiculoDTO);

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(command.update(veiculoDTO)).thenReturn(veiculo);
        when(mapper.map(any(), any())).thenReturn(veiculoDTO);

        ResponseEntity<VeiculoDTO> response = controller.update(ID, veiculoDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(VeiculoDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(MARCA, response.getBody().getMarca());
        assertEquals(MODELO, response.getBody().getModelo());
        assertEquals(PLACA_VEICULO, response.getBody().getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getBody().getKmPercorrido());
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(command).delete(anyInt());

        ResponseEntity<VeiculoDTO> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(command, times(1)).delete(anyInt());
    }

    private void startVeiculo() {
        veiculo = new Veiculo(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
        veiculoDTO = new VeiculoDTO(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
    }
}