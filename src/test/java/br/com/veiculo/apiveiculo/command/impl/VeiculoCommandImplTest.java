package br.com.veiculo.apiveiculo.command.impl;

import br.com.veiculo.apiveiculo.model.Veiculo;
import br.com.veiculo.apiveiculo.model.dto.VeiculoDTO;
import br.com.veiculo.apiveiculo.repositories.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class VeiculoCommandImplTest {

    public static final int ID = 1;
    public static final String MARCA = "bmw";
    public static final String MODELO = "320i";
    public static final String PLACA_VEICULO = "BRB2E19";
    public static final String KM_PERCORRIDO = "30000";
    @InjectMocks
    private VeiculoCommandImpl command;
    @Mock
    private VeiculoRepository repository;

    @Mock
    private ModelMapper mapper;

    private Veiculo veiculo;
    private VeiculoDTO veiculoDTO;

    private Optional<Veiculo> optionalVeiculo;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startVeiculo();
    }

    @Test
    void whenFindByIdThenReturnAVeiculoInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalVeiculo);

        Veiculo response = command.findById(ID);

        assertNotNull(response);

        assertEquals(Veiculo.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MARCA, response.getMarca());
        assertEquals(MODELO, response.getModelo());
        assertEquals(PLACA_VEICULO, response.getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getKmPercorrido());
    }


    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startVeiculo() {
        veiculo = new Veiculo(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
        veiculoDTO = new VeiculoDTO(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
        optionalVeiculo = Optional.of(new Veiculo(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO));
    }
}