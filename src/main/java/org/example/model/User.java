package org.example.model;

import java.util.Objects;

public class User implements Comparable<User>{
    private String name;
    private int points;

    public User(String name) {
        this.name = name;
        this.points = 0;
    }

    public User(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return !name.equals(user.name);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public int compareTo(User user) {
        if(user.points == getPoints())
            return user.getName().compareTo(getName());

        return getPoints().compareTo(user.getPoints());
    }
}