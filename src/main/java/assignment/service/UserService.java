package assignment.service;

import assignment.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
