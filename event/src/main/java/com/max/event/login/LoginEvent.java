package com.max.event.login;

import com.max.base.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginEvent {
    @Autowired
    private List<LoginEventHandle> loginEventHandles;

    public void handle(UserDto user) {
        for (LoginEventHandle handler : loginEventHandles) {
            handler.handle(user);
        }
    }
}
