package cz.fel.cvut.sitforms.service.impl;

import cz.fel.cvut.sitforms.dto.TokenResponseDto;
import cz.fel.cvut.sitforms.dto.UserRequestDto;
import cz.fel.cvut.sitforms.dto.UserResponseDto;
import cz.fel.cvut.sitforms.mapper.UserMapper;
import cz.fel.cvut.sitforms.model.RoleEntity;
import cz.fel.cvut.sitforms.model.Subscription;
import cz.fel.cvut.sitforms.model.SubscriptionType;
import cz.fel.cvut.sitforms.model.UserEntity;
import cz.fel.cvut.sitforms.repository.RoleRepository;
import cz.fel.cvut.sitforms.repository.UserRepository;
import cz.fel.cvut.sitforms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username doesnt exist");
        }
        return new User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleEntity().getName()));
        return authorities;
    }

    @Override
    public List<UserResponseDto> findUsers() {
        return null;
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public TokenResponseDto createUser(UserRequestDto userRequestDto) {
        log.info("Creating an user | User request dto - {}", userRequestDto);
        UserEntity user = userMapper.toEntity(userRequestDto);

        log.info("Creating an admin | Controlling if username is free");
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new RuntimeException("Admin with such email already exist");
        }
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        RoleEntity role = roleRepository.findByName("FREE_USER");
        log.info("Creating an new user | Adding an user role - {}", role);
        user.setRoleEntity(role);
        user.setSubscription(new Subscription().setSubscriptionType(SubscriptionType.FREE));
        UserEntity save = userRepository.save(user);
        System.out.println(user.getName()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return null;
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        System.out.println(user.getName()); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        return userMapper.toResponse(user);
    }

    @Override
    public UserRequestDto updateUser(UserRequestDto userRequestDto, Long userId) {
        return null;
    }

    @Override
    public void initImport() {
        UserRequestDto userRequestDto = new UserRequestDto().setUsername("free").setPassword("123");
        createUser(userRequestDto);
    }
}
