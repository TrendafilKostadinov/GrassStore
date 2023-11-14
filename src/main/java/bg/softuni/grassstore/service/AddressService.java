package bg.softuni.grassstore.service;

import bg.softuni.grassstore.model.dto.AddressDetailDTO;
import bg.softuni.grassstore.model.entity.AddressEntity;
import bg.softuni.grassstore.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository,
                          ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }


    public void addAddress(AddressDetailDTO addressAddDTO) {
        AddressEntity address = modelMapper.map(addressAddDTO, AddressEntity.class);

        addressRepository.save(address);
    }

    public List<AddressDetailDTO> getAllAddresses(){
        return addressRepository
                .findAll()
                .stream()
                .map(addressEntity ->  modelMapper.map(addressEntity, AddressDetailDTO.class))
                .toList();
    }

    public AddressEntity getAddress(Long id){
        return addressRepository.findById(id).orElse(null);
    }
}
