package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import main.dto.LoginDTO;
import main.dto.UserDTO;
import main.entity.User;
import main.service.LoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping
	public ResponseEntity<UserDTO> logar(@RequestBody LoginDTO loginDTO) {
		try {
			return ResponseEntity.ok(loginService.logar(loginDTO));
		}catch(AuthenticationException ex) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("deslogar")
	public ResponseEntity<HttpStatus> logout() {

		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

    @PostMapping("/cadastrar")
    public ResponseEntity<UserDTO> include(@RequestBody User user) {
        try {
            return ResponseEntity.ok(loginService.include(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
	
}
