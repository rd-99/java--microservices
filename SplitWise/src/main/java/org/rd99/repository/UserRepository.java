package org.rd99.repository;

import lombok.Getter;
import lombok.Setter;
import org.rd99.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
public class UserRepository {
    Map<String, User> usersRepo;
    List<User> userList ;
    public UserRepository(){
        usersRepo = new HashMap<>();
        userList = new ArrayList<>();
    }
}
