package com.E_Commerce_Application.E_Commerce.site.Application.User;


import com.E_Commerce_Application.E_Commerce.site.Application.DTO.LoginDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }

    public String authenticate(LoginDTO login){
        Optional<User> maybeUser = userRepository.findByEmail(login.getEmail());
        if(maybeUser.isPresent()) {
            User user = maybeUser.get();
            String password = login.getPassword();
            String encodePassword = user.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
            if (isPwdRight) {
                    return "Login Success";
                } else {
                    return "Login Failed";
                }
        }else {
            return "Email not Exist";
            }
        }
}

