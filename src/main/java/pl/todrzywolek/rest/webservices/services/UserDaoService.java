package pl.todrzywolek.rest.webservices.services;

import org.springframework.stereotype.Service;
import pl.todrzywolek.rest.webservices.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User("1", "Adam", new Date()));
        users.add(new User("2", "Ewa", new Date()));
        users.add(new User("3", "Brian", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        users.add(user);
        userCount++;
        return user;
    }

    public User findOne(User user) {
        return users.stream().filter(user1 -> user.getId().equals(user1.getId())).findFirst().orElse(null);
    }
}
