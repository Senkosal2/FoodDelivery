package group.fooddelivery.main.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import group.fooddelivery.main.dto.UserDetailDTO;
import group.fooddelivery.main.entity.UserDetail;
import group.fooddelivery.main.exception.BadRequestException;
import group.fooddelivery.main.exception.NotFoundException;
import group.fooddelivery.main.mapper.UserMapper;
import group.fooddelivery.main.repository.UserRepository;
import group.fooddelivery.main.utils.Global;
import group.fooddelivery.main.utils.Global.UserType;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public UserDetailDTO getUser(int userId) {
        UserDetail existingUser = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException("User does not exist!")
        );
        return userMapper.toUserDTO(existingUser);
    }

    @Transactional(readOnly=true) // ensure db safely, but allow only read, no write
    public List<UserDetailDTO> getUsers() {
        List<UserDetail> allExistingUsers = userRepository.findAll();
        if (allExistingUsers.isEmpty()) {
            throw new NotFoundException("No users!");
        }
        return userMapper.toUserDTOs(allExistingUsers);
    }

    @Transactional // ensure db safely in case of error
    public void createUser(UserDetailDTO userDTO) {
        Map<String, String> invalid = new LinkedHashMap<>();
        if (userRepository.findByPhoneNumber(userDTO.getPhoneNumber()) != null) {
            invalid.clear();
            invalid.put("phoneNumber", "Phone Number is already existed!");
            throw new BadRequestException(invalid);
        }
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            invalid.clear();
            invalid.put("username", "Username is already existed!");
            throw new BadRequestException(invalid);
        }
        UserDetail user = userMapper.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserType(userDTO.getUserType() == null ? UserType.USER : userDTO.getUserType());
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(int userId, UserDetailDTO userDTO) {
        UserDetail existingUser = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException("User does not exist!")
        );
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(Global.encrptPassword(userDTO.getPassword()));
        existingUser.setDateOfBirth(userDTO.getDateOfBirth());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());

        userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUser(int userId) {
        UserDetail existingUser = userRepository.findById(userId).orElseThrow(
            () -> new NotFoundException("User does not exist!")
        );
        userRepository.delete(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User does not exist");
        }
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(), 
            user.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserType().name())));
    }
}
