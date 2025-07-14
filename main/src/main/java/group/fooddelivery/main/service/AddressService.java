package group.fooddelivery.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.AddressDTO;
import group.fooddelivery.main.entity.Address;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.AddressMapper;
import group.fooddelivery.main.repository.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional(readOnly = true)
    public AddressDTO getAddress(int id) {
        Address address = addressRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Address not found!"));
        return addressMapper.toAddressDTO(address);
    }

    @Transactional(readOnly = true)
    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        if (addresses.isEmpty()) {
            throw new NotFoundException("No addresses found!");
        }
        return addressMapper.toAddressDTOs(addresses);
    }

    @Transactional
    public void createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toAddress(addressDTO);
        addressRepository.save(address);
    }

    @Transactional
    public void updateAddress(int id, AddressDTO addressDTO) {
        Address existing = addressRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Address not found!"));

        existing.setAddress(addressDTO.getAddress());
        addressRepository.save(existing);
    }

    @Transactional
    public void deleteAddress(int id) {
        Address address = addressRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Address not found!"));
        addressRepository.delete(address);
    }
}
