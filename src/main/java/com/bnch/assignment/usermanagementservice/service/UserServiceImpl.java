package com.bnch.assignment.usermanagementservice.service;

import com.bnch.assignment.usermanagementservice.entity.Event;
import com.bnch.assignment.usermanagementservice.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;
    @Override
    public User createEvent(User user) {
        Event eventRes=restTemplate.postForObject("http://localhost:8080/api/events",user.getEvent(),Event.class);
        user.setEvent((List<Event>) eventRes);
        return user;

    }

    @Override
    public void updateEvent(User user) {
        restTemplate.put("http://localhost:8080/api/events"+ user.getEvent().get(0).getId(),user.getEvent(),Event.class);

    }

    @Override
    public void deleteEvent(User user) {
        restTemplate.delete("http://localhost:8080/api/events"+user.getEvent().get(0).getId(),user.getEvent(),Event.class);
    }

}
