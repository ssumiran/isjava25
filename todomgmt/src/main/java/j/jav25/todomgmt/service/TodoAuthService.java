package j.jav25.todomgmt.service;

import j.jav25.todomgmt.dto.TodoLoginDto;
import j.jav25.todomgmt.dto.TodoRegisterDto;

public interface TodoAuthService {
    String register(TodoRegisterDto registerDto);

    String login(TodoLoginDto loginDto);
}
