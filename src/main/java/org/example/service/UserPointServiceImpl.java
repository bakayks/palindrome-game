package org.example.service;

import org.example.exception.CustomGameException;
import org.example.model.User;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UserPointServiceImpl implements UserPointsService{

    private TreeSet<User> users = new TreeSet<>();

    @Override
    public User addNewUser(User newUser) throws CustomGameException {
        var user = findByUsername(newUser.getName());

        if(user == null) {
            user = new User(newUser.getName(), 0);
            users.add(user);
        }else {
            throw new CustomGameException("Игрок с таким именем уже существует!");
        }

        return user;
    }

    @Override
    public void addPoints(User user, Integer points) {
        var existingUser = findByUsername(user.getName());
        users.remove(existingUser);
        existingUser.addPoints(points);
        users.add(existingUser);
    }

    @Override
    public List<User> getLeaderboard() {
        return users.descendingSet()
                .stream()
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public User findByUsername(String username) {
        var userOpt = users.stream()
                .filter(e -> e.getName().equals(username))
                .findFirst();
        return userOpt.orElse(null);
    }
}
