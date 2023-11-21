package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.AddressDetailDTO;
import bg.softuni.grassstore.model.entity.AddressEntity;
import bg.softuni.grassstore.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addAddress() {

        AddressDetailDTO addressDetailDTO = new AddressDetailDTO();
        addressDetailDTO.setStreet("Sample Street");
        addressDetailDTO.setCity("Sample City");
        addressDetailDTO.setCountry("Sample Country");

        when(modelMapper.map(addressDetailDTO, AddressEntity.class)).thenReturn(new AddressEntity());

        addressService.addAddress(addressDetailDTO);

        verify(addressRepository, times(1)).save(any());

    }

    @Test
    void getAllAddresses() {

        when(addressRepository.findAll()).thenReturn(Collections.emptyList());

        when(modelMapper.map(null, AddressDetailDTO.class)).thenReturn(null);

        List<AddressDetailDTO> result = addressService.getAllAddresses();

        verify(addressRepository, times(1)).findAll();

        assertEquals(Collections.emptyList(), result);

    }

    @Test
    void getAddress() {

        when(addressRepository.findById(1L)).thenReturn(Optional.of(new AddressEntity()));

        when(modelMapper.map(null, AddressDetailDTO.class)).thenReturn(null);

        AddressEntity result = addressService.getAddress(1L);

        verify(addressRepository, times(1)).findById(1L);

        assertNotNull(result);

    }
}