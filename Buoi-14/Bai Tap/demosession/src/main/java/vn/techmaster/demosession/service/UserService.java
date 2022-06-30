package vn.techmaster.demosession.service;

import java.util.Optional;

import vn.techmaster.demosession.model.User;

public interface UserService {
    public User login(String email, String password);
    public boolean logout(String email);

    public User addUser(String name, String email, String password);
    public User addUserThenAutoActivate(String name, String email, String password);
    public User activateUser(String activate_code);

    public Boolean updatePassword(String email, String password);
    public Boolean updateEmail(String email, String newEmail);

    public Optional<User> findByEmail(String email);
    public User findById(String id);
}
