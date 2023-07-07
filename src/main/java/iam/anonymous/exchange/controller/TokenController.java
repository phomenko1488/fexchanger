package iam.anonymous.exchange.controller;

import iam.anonymous.exchange.dto.TokenDTO;
import iam.anonymous.exchange.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tokens")
@CrossOrigin(value = "*", origins = "*", allowedHeaders = "*", originPatterns = "*")
public class TokenController {
    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(tokenService.getAll().stream().map(TokenDTO::new).collect(Collectors.toList()));
    }
}
