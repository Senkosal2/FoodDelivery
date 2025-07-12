package group.fooddelivery.main.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import group.fooddelivery.main.dto.UserDTO;
import group.fooddelivery.main.entity.User;

@Component
public class UserMapper {
    
    @Autowired
    private AddressMapper addressMapper;

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setAddress(addressMapper.toAddress(userDTO.getAddressDTO()));

        return user;
    }
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddressDTO(addressMapper.toAddressDTO(user.getAddress()));
        return userDTO;
    }
}