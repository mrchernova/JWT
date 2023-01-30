package com.example.jwt.controller;


import com.example.jwt.config.AuthRequest;
import com.example.jwt.config.AuthResponse;
import com.example.jwt.config.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



@RestController
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtTokenUtil;


    /**
     * Пользователь делает POST-запрос с именем и паролем по адресу /authenticate,
     * а в ответ получает сгенерированынй токен.
     * Токен генерится методом generateToken() из config/JWTUtil.
     *
     * Настраиванем какой должен быть запрос и какой ответ (config/AuthRequest.java, config/AuthResponse.java)
     *
     *
     * Если имя и пароль верные, токен возвращается в AuthResponse, а если нет — выбрасывается исключение и на фронтенд приходит сообщение об ошибке.
     *
     * Фронтенд сохраняет у себя JWT-токен, и потом использует его при каждом запросе.
     *
     * https://sysout.ru/primer-prilozheniya-s-jwt-tokenom/
     */

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        // при создании токена в него кладется username как Subject и список authorities как кастомный claim
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

//        return new AuthResponse(jwt);
        return new AuthResponse();      //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
}