package vn.techmaster.demosession.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import vn.techmaster.demosession.exception.UserException;
import vn.techmaster.demosession.hash.Hashing;
import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;
import vn.techmaster.demosession.repository.UserRepo;

@Service
@AllArgsConstructor
public class UserServiceImMemory implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Hashing hashing;

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findByEmail(email);
        if (!o_user.isPresent()) {
            throw new UserException("User not found");
        }
        User user = o_user.get();

        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is not active");
        }

        if (!hashing.validatePassword(password, user.getHashed_password())) {
            throw new UserException("Password incorrect");
        }

        return user;
    }

    @Override
    public boolean logout(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public User addUser(String name, String email, String password) {
        String hashed_password = hashing.hashPassword(password);
        User user = userRepo.addUserPending(name, email, hashed_password);
        return user;
    }

    @Override
    public User addUserThenAutoActivate(String name, String email, String password) {
        String hashed_password = hashing.hashPassword(password);
        User user = userRepo.addUser(name, email, State.ACTIVE, hashed_password);
        return user;
    }

    @Override
    public User activateUser(String activate_code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
