package com.in28minutes.rest.webservices.restfulwebservices.user;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<user> users = new ArrayList<>();
    private static int usersCount=0;

    static {
        users.add(new user(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new user(++usersCount, "Eve", LocalDate.now().minusYears(25)));
        users.add(new user(++usersCount, "jim", LocalDate.now().minusYears(20)));
    }

    public user save(user User){
        User.setId(++usersCount);
        users.add(User);
        return User;
    }

    public List<user> findAll() {
        return users;
    }

    public user findOne(int id) {
        for (user User : users) {
            if (User.getId() == id) {
                return User;
            }

        }

        return null;
    }

    public void deletebyId(int id){
        Predicate<? super user> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);

    }
}

