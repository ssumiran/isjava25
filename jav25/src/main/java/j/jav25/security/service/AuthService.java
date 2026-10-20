package j.jav25.security.service;

import j.jav25.security.dto.LoginDto;
import j.jav25.security.dto.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);

}
