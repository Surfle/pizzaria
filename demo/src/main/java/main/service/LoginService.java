//AuthenticationService.java
package main.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import main.config.JwtServiceGenerator;
import main.dto.ClienteDto;
import main.dto.LoginDTO;
import main.dto.UserDTO;
import main.entity.Cliente;
import main.entity.User;
import main.repository.LoginRepository;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDTO logar(LoginDTO loginDTO) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getUsername(),
						loginDTO.getPassword()
						)
				);
		User user = repository.findByUsername(loginDTO.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		
		return toUserDTO(user, jwtToken);
	}


	private UserDTO toUserDTO(User user, String token) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setToken(token);
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}
   
	public UserDTO include(User user) {
        Assert.notNull(user.getUsername(), "Username não informado!");
        Assert.notNull(user.getPassword(), "Password não informado!");
        Assert.notNull(user.getRole(), "Role não informada!");
        
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());        
        userDTO.setRole(user.getRole());
        userDTO.setToken(user.getPassword());

        repository.save(user);

        return userDTO;
    }
}
