package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.CustomerAddDTO;
import bg.softuni.grassstore.model.dto.CustomerDetailDTO;
import bg.softuni.grassstore.model.dto.UserDetailDTO;
import bg.softuni.grassstore.model.entity.CustomerEntity;
import bg.softuni.grassstore.model.entity.UserEntity;
import bg.softuni.grassstore.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

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

        UserDetails userDetails = getCurrentUser();


        CustomerEntity customer = this.map(customerAddDTO, userDetails.getUsername());

        customerRepository.save(customer);

        return true;
    }

    public List<CustomerDetailDTO> getAllCustomersByTrader(){

        UserDetailDTO currentUser = userService.getUser(getCurrentUser().getUsername());

        return customerRepository
                .findAllByTrader_Id(currentUser.getId())
                .stream()
                .map(customerEntity -> modelMapper.map(customerEntity, CustomerDetailDTO.class))
                .toList();
    }

    private static UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
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

    public CustomerDetailDTO getCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow();
        return modelMapper.map(customerEntity, CustomerDetailDTO.class);
    }

    //TODO:error handling

}
