package com.codewithmosh.store.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewithmosh.store.dtos.RegisterUserRequest;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            // @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort" // the param name we'll get in query
            ) String sortBy) {
        // System.out.println(authToken);
        if (!Set.of("name", "email").contains(sortBy))
            sortBy = "name";
        return userRepository.findAll(Sort.by(sortBy).ascending())
                .stream()
                // .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail()))
                // .map(user -> userMapper.toDto(user))
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }

        // return new ResponseEntity<>(user, HttpStatus.OK);
        // UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var user = userMapper.toEntity(request);
        userRepository.save(user);
        var userDto = userMapper.toDto(user);
        var uri = uriComponentsBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

}
