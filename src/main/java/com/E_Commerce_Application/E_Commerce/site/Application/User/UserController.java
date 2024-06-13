package com.E_Commerce_Application.E_Commerce.site.Application.User;

import com.E_Commerce_Application.E_Commerce.site.Application.DTO.LoginDTO;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String testApi(){
        return "TestApi works.";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.register(user));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return userService.authenticate(loginDTO);
    }

    /*@PutMapping("/{id}/{role}")
    public ResponseEntity<User> updateRole(@PathVariable int id, @PathVariable String role){
        return service.update(id, role);
    }*/
}
