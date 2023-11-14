package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.CustomerAddDTO;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerRepository customerRepository,
                           AddressService addressService,
                           UserService userService,
                           ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    public boolean addCustomer(CustomerAddDTO customerAddDTO) {
        if (customerRepository.findByName(customerAddDTO.getName()).isPresent()){
            return false;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        CustomerEntity customer = this.map(customerAddDTO, userDetails.getUsername());

        customerRepository.save(customer);

        return true;
    }

    private CustomerEntity map(CustomerAddDTO customerAddDTO, String email) {

        UserEntity trader = modelMapper
                .map(userService.getUser(email), UserEntity.class);

        return new CustomerEntity()
                .setName(customerAddDTO.getName())
                .setAddress(addressService.getAddress(customerAddDTO.getAddress()))
                .setContactPerson(customerAddDTO.getContactPersonEmail())
                .setVatNumber(customerAddDTO.getVatNumber())
                .setPhone(customerAddDTO.getPhone())
                .setTrader(trader);
    }
}
