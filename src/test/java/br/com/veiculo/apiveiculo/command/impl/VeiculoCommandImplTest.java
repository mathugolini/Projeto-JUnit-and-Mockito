package br.com.veiculo.apiveiculo.command.impl;


import br.com.veiculo.apiveiculo.command.exceptions.DataIntegratyViolationException;
import br.com.veiculo.apiveiculo.command.exceptions.ObjectNotFoundException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class VeiculoCommandImplTest {

    public static final int ID = 1;
    public static final String MARCA = "bmw";
    public static final String MODELO = "320i";
    public static final String PLACA_VEICULO = "BRB2E19";
    public static final String KM_PERCORRIDO = "30000";
    public static final String VEICULO_NAO_ENCONTRADO = "Veículo não encontrado";
    public static final int INDEX = 0;
    public static final String PLACA_DE_VEICULO_JA_CADASTRADO_NO_SISTEMA = "Placa de veículo ja cadastrado no sistema";
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
    void whenFindByIdThenReturnAVeiculoNotFound() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(VEICULO_NAO_ENCONTRADO));

        try {
            command.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(VEICULO_NAO_ENCONTRADO, ex.getMessage());
        }
    }


    @Test
    void whenFindAllThenReturnAnListOfVeiculos() {
        when(repository.findAll()).thenReturn(List.of(veiculo));

        List<Veiculo> response = command.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Veiculo.class, response.get(INDEX).getClass());
        assertEquals(veiculo, response.get(INDEX));

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(MARCA, response.get(INDEX).getMarca());
        assertEquals(MODELO, response.get(INDEX).getModelo());
        assertEquals(PLACA_VEICULO, response.get(INDEX).getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.get(INDEX).getKmPercorrido());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(veiculo);

        Veiculo response = command.create(veiculoDTO);

        assertNotNull(response);
        assertEquals(Veiculo.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MARCA, response.getMarca());
        assertEquals(MODELO, response.getModelo());
        assertEquals(PLACA_VEICULO, response.getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getKmPercorrido());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByPlacaVeiculo(anyString())).thenReturn(optionalVeiculo);

        try {
            optionalVeiculo.get().setId(2);
            command.create(veiculoDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Placa de veículo já cadastrado no sistema", ex.getMessage());
        }
    }


    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(veiculo);

        Veiculo response = command.update(veiculoDTO);

        assertNotNull(response);
        assertEquals(Veiculo.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(MARCA, response.getMarca());
        assertEquals(MODELO, response.getModelo());
        assertEquals(PLACA_VEICULO, response.getPlacaVeiculo());
        assertEquals(KM_PERCORRIDO, response.getKmPercorrido());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByPlacaVeiculo(anyString())).thenReturn(optionalVeiculo);

        try {
            optionalVeiculo.get().setId(2);
            command.create(veiculoDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Placa de veículo já cadastrado no sistema", ex.getMessage());
        }
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalVeiculo);
        doNothing().when(repository).deleteById(anyInt());
        command.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException(VEICULO_NAO_ENCONTRADO));

        try {
            command.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertNotNull(VEICULO_NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startVeiculo() {
        veiculo = new Veiculo(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
        veiculoDTO = new VeiculoDTO(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO);
        optionalVeiculo = Optional.of(new Veiculo(ID, MARCA, MODELO, PLACA_VEICULO, KM_PERCORRIDO));
    }
}