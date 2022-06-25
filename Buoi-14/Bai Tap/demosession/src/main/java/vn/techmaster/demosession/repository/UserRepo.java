package vn.techmaster.demosession.repository;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.demosession.model.State;
import vn.techmaster.demosession.model.User;

@Repository
public class UserRepo {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public User addUser(String fullname, String email, State state, String hashed_password){
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                    .fullname(fullname)
                    .email(email)
                    .hashed_password(hashed_password)
                    .build();
        users.put(id, user);
        return user;
    }
}
