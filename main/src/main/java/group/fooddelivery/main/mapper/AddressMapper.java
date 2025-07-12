package group.fooddelivery.main.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.AddressDTO;
import group.fooddelivery.main.entity.Address;

@Component
public class AddressMapper {

    @Autowired
    private UserMapper userMapper;

    public Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setAddress(addressDTO.getAddress());
        address.setUser(userMapper.toUser(addressDTO.getUserDTO()));
        return address;
    }

    public AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddress(address.getAddress());
        addressDTO.setUserDTO(userMapper.toUserDTO(address.getUser()));
        return addressDTO;
    }
}
