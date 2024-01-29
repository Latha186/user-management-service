package com.bnch.assignment.usermanagementservice.service;

import com.bnch.assignment.usermanagementservice.entity.Event;
import com.bnch.assignment.usermanagementservice.entity.User;

import java.sql.Date;
import java.util.List;

public interface UserService {

    User createEvent(User user);
    void updateEvent(User user);

    void deleteEvent(User user);

}
