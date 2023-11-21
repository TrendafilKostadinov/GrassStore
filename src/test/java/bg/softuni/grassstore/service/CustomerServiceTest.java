package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.CustomerAddDTO;
import bg.softuni.grassstore.model.dto.CustomerDetailDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addCustomer() {

        CustomerAddDTO customerAddDTO = new CustomerAddDTO();
        customerAddDTO.setName("Sample Customer");

        when(customerRepository.findByName(customerAddDTO.getName())).thenReturn(Optional.empty());

        mockAuthentication("sampleUser");
        when(userService.getUser(anyString())).thenReturn(new UserDetailDTO());

        when(modelMapper.map(customerAddDTO, String.class)).thenReturn("SampleEmail");
        when(modelMapper.map("SampleEmail", UserEntity.class)).thenReturn(new UserEntity());

        boolean result = customerService.addCustomer(customerAddDTO);

        verify(customerRepository, times(1)).save(any());

        assertTrue(result);

    }

    @Test
    void getAllCustomersByTrader() {

        mockAuthentication("sampleUser");
        when(userService.getUser(anyString())).thenReturn(new UserDetailDTO());

        when(customerRepository.findAllByTrader_Id(1L)).thenReturn(Collections.emptyList());

        when(modelMapper.map(null, CustomerDetailDTO.class)).thenReturn(null);

        List<CustomerDetailDTO> result = customerService.getAllCustomersByTrader();

        assertEquals(Collections.emptyList(), result);

    }

    @Test
    void getCustomer() {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new CustomerEntity()));

        when(customerService.getCustomer(1L)).thenReturn(new CustomerDetailDTO());

        CustomerDetailDTO result = customerService.getCustomer(1L);

        verify(customerRepository, times(2)).findById(1L);

        assertNotNull(result);

    }

    @Test
    void deleteCustomer() {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new CustomerEntity()));

        boolean result = customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(1L);

        assertTrue(result);

    }

    private void mockAuthentication(String username) {
        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(username);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }
}