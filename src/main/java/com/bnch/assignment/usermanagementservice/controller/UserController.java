package com.bnch.assignment.usermanagementservice.controller;


import com.bnch.assignment.usermanagementservice.entity.Event;
import com.bnch.assignment.usermanagementservice.entity.User;
import com.bnch.assignment.usermanagementservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/userEvents")
public class UserController {
    private UserService userService;

    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<String> createUserEvent(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<? extends GrantedAuthority> role_admin = authentication.getAuthorities().stream().filter(role -> role.getAuthority().equals(user.getRole())).findAny();
        role_admin.orElseThrow(() -> new AccessDeniedException("user forbidden to create an event"));
        User saveEvent = userService.createEvent(user);
        return new ResponseEntity<>("admin successfully created the event", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUserEvent(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<? extends GrantedAuthority> role_admin = authentication.getAuthorities().stream().filter(role -> role.getAuthority().equals(user.getRole())).findAny();
        role_admin.orElseThrow(() -> new AccessDeniedException("user forbidden to update an event"));
        userService.updateEvent(user);
        return new ResponseEntity<>("admin successfully updated the event", HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserEvent(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<? extends GrantedAuthority> role_admin = authentication.getAuthorities().stream().filter(role -> role.getAuthority().equals(user.getRole())).findAny();
        role_admin.orElseThrow(() -> new AccessDeniedException("user forbidden to delete an event"));
        userService.deleteEvent(user);
        return new ResponseEntity<>("admin successfully deleted the event", HttpStatus.CREATED);

    }

    @GetMapping("/userEventsByDate")
    public ResponseEntity<User> eventsByDate(
            @RequestParam(name = "dateCreatedFrom", defaultValue = "") Date dateCreatedFrom,
            @RequestParam(name = "dateCreatedTo", defaultValue = "") Date dateCreatedTo) {
        ResponseEntity<Event> response = restTemplate.getForEntity("http://localhost:8080/api/events/eventsByDate?dateCreatedFrom=" + dateCreatedFrom + "&dateCreatedTo=" + dateCreatedTo, Event.class);
        User user = new User();
        user.setEvent((List<Event>) response);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/userEventsByTitle")
    public ResponseEntity<User> eventsByTitle(
            @RequestParam(name = "title", defaultValue = "") String title) {
        ResponseEntity<Event> response = restTemplate.getForEntity("http://localhost:8080/api/events/eventsByTitle?title=" + title, Event.class);
        User user = new User();
        user.setEvent((List<Event>) response);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
