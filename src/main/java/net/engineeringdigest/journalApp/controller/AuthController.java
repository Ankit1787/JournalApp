package net.engineeringdigest.journalApp.controller;

import lombok.RequiredArgsConstructor;
import net.engineeringdigest.journalApp.dto.LoginRequestDto;
import net.engineeringdigest.journalApp.dto.LoginResponseDto;
import net.engineeringdigest.journalApp.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthController {

    private  final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto>login (@RequestBody LoginRequestDto loginRequestDto){
        return  new ResponseEntity<>(authService.login(loginRequestDto), HttpStatus.ACCEPTED);

    }

}
