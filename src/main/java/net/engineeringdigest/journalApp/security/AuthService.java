package net.engineeringdigest.journalApp.security;

import lombok.RequiredArgsConstructor;
import net.engineeringdigest.journalApp.dto.LoginRequestDto;
import net.engineeringdigest.journalApp.dto.LoginResponseDto;
import net.engineeringdigest.journalApp.entity.UserEntry;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private  final AuthUtil authUtil;
    public LoginResponseDto login (LoginRequestDto loginRequestDto){
         Authentication authentication=    authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        UserEntry user = (UserEntry)authentication.getPrincipal();
        String token =authUtil.generateToken(user);
        return new LoginResponseDto(token,user.getId());
    }
}
