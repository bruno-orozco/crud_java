package com.platzi.springboot.controller;

import com.crud.springboot.entity.Posts;
import com.crud.springboot.entity.User;
import com.crud.springboot.repository.PostRepository;
import com.crud.springboot.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class CrudRestController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CrudRestController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/posts")
    List<Posts> allPost() {
        return postRepository.findAll();
    }

    @PostMapping("/")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }


    @GetMapping("/{id}")
    User getOne(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(user -> {
                    user.setEmail(newUser.getEmail());
                    user.setBirthDate(newUser.getBirthDate());
                    user.setName(newUser.getName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }


    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        userRepository.deleteById(id);
    }


    @GetMapping("/pageable")
    public List<User> getUserPageable(@RequestParam int page, @RequestParam int size) {
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }

}
