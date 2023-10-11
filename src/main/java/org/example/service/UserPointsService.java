package org.example.service;

import org.example.exception.CustomGameException;
import org.example.model.User;

import java.util.List;

public interface UserPointsService {

    User addNewUser(User user) throws CustomGameException;

    void addPoints(User user, Integer points);

    User findByUsername(String username);

    List<User> getLeaderboard();
}
