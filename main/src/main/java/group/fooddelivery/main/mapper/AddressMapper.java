package group.fooddelivery.main.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.AddressDTO;
import group.fooddelivery.main.entity.Address;

@Component
public class AddressMapper {

    // @Autowired
    // @Lazy
    // private UserMapper userMapper;

    public Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setAddress(addressDTO.getAddress());
        // address.setUser(userMapper.toUser(addressDTO.getUserDTO()));
        return address;
    }

    public List<Address> toAddresses(List<AddressDTO> addressDTOs) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : addressDTOs) {
            addresses.add(toAddress(addressDTO));
        }
        return addresses;
    }

    public AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddress(address.getAddress());
        // addressDTO.setUserDTO(userMapper.toUserDTO(address.getUser()));
        return addressDTO;
    }

    public List<AddressDTO> toAddressDTOs(List<Address> addresses) {
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for (Address address : addresses) {
            addressDTOs.add(toAddressDTO(address));
        }
        return addressDTOs;
    }
}
