package me.luraframework.auth.security;

import lombok.Data;
import lombok.Value;

@Data
@Value(staticConstructor = "of")
public class OnlineUser {
    private String username;
}
