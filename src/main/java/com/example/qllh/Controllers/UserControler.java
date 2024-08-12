package com.example.qllh.Controllers;

import com.example.qllh.DTO.UserDTO.UserRequest;
import com.example.qllh.Services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserControler {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUsersById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getPageUsers(@RequestParam(value = "textSearch", defaultValue = "") String textSearch,
                                         @RequestParam(value = "sortData", defaultValue = "id") String sortData,
                                         @RequestParam(value = "sortType", defaultValue = "asc") String sortType,
                                         @RequestParam(value = "currentPage", defaultValue = "1") Long currentPage,
                                         @RequestParam(value = "limit", defaultValue = "1") Long limit) {
        return new ResponseEntity<>(userService.getPageUsers(textSearch, currentPage, limit, sortData, sortType), HttpStatus.OK);
    }
}
