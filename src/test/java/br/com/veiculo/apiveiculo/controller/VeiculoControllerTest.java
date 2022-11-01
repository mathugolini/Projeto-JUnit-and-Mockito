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


@SpringBootTest
class VeiculoControllerTest {

    public static final int ID = 1;
    public static final String MARCA = "bmw";
    public static final String MODELO = "320i";
    public static final String PLACA_VEICULO = "BRB2E19";
    public static final String KM_PERCORRIDO = "30000";
    public static final String VEICULO_NAO_ENCONTRADO = "Veículo não encontrado";
    public static final int INDEX = 0;

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
    void findById() {
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
    }
}