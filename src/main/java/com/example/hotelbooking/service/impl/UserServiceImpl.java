package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.domain.User;
import com.example.hotelbooking.entity.UserEntity;
import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.exception.UserAlreadyExistsException;
import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.mapper.UserMapper;
import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final BookingMapper bookingMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDomain)
                .toList();
    }

    @Override
    public User findById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .map(userMapper::toDomain)
                .orElseThrow(
                        () -> new EntityNotFoundException("Пользователь с таким id не найден.")
                );
    }

    @Override
    public User findByUsername(String username) throws EntityNotFoundException {
        return userRepository.findByUsername(username)
                .map(userMapper::toDomain)
                .orElseThrow(
                        () -> new EntityNotFoundException("Пользователь с таким username не найден.")
                );
    }

    @Override
    public User save(User user) throws UserAlreadyExistsException {

        if (userRepository.existsByUsernameAndEmail(user.getUsername(), user.getEmail()))
            throw new UserAlreadyExistsException("Пользователь с таким username и email уже зарегистрирован в системе!");

        UserEntity newUser = userMapper.toEntity(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userRepository.save(newUser);

        return userMapper.toDomain(savedUser);
    }

    @Override
    public User update(Long id, User user) throws EntityNotFoundException {

        UserEntity existing = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Пользователь с таким id не найден.")
        );

        existing.setBookings(user.getBookings().stream()
                .map(bookingMapper::toEntity)
                .collect(Collectors.toSet()));
        existing.setRole(user.getRole());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());

        UserEntity saved = userRepository.save(existing);

        return userMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id);
    }

}