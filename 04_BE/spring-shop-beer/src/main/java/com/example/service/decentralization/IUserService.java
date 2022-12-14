package com.example.service.decentralization;


import com.example.dto.decentralization.IUserEmailDto;
import com.example.model.decentralization.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String existsByUserName(String username);

    Optional<User> findUserByUsername(String username);

    List<User> findAll();

    User findByUsername(String name);


    void updatePassword(User user, String newPassword);

    void saveCreateGmail(User user);

    Optional<User> showUsername(String username);

    void updateUser(User user, String username);
    Optional<IUserEmailDto> findByEmail(String email);

    Optional<IUserEmailDto> findByUsernameDto(String username);

}
