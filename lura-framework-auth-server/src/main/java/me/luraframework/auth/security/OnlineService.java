package me.luraframework.auth.security;

import me.luraframework.auth.security.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OnlineService {

    private static final Map<String, OnlineUser> ONLINE_USER = new HashMap<>();

    public void addUser(String token, User user) {
        ONLINE_USER.put(token, OnlineUser.of(user.getUsername()));
    }

    public void removeUser(String token) {
        ONLINE_USER.remove(token);
    }

    public OnlineUser getOne(String token) {
        return ONLINE_USER.get(token);
    }
}
