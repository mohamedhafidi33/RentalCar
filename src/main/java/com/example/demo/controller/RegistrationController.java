package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Client;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.UserRepository;
import com.exemple.demo.token.ConfirmationToken;
import com.exemple.demo.token.ConfirmationTokenRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {
	
	private final UserRepository userRepo;
	private final ClientRepository clientRepository;
	 private final PasswordEncoder passwordEncoder;
	// private final ConfirmationTokenRepository confirmationTokenRepository;	 
	 
	 


	@GetMapping
	public String registrationForm() {
		return "register";
	}
	
	 @PostMapping
	 public String processRegistration(
			 @RequestParam("name")String name,
			 @RequestParam("prenom")String prenom,
			 @RequestParam("telephone")String telephone,
			 @RequestParam("addresse")String addresse,
			 @RequestParam("pays")String pays,
			 @RequestParam("birthday")Date dateNaissance,
			 @RequestParam("email")String email,
			 @RequestParam("username")String username,
			 @RequestParam("password")String password
			 ) {
		 User user=new User();
		 user.setUsername(username);
		 user.setPassword(passwordEncoder.encode(password));
		 user.setRole(Role.valueOf("USER"));
		 userRepo.save(user);
		 Client client=new Client();
		 client.setAdresse(addresse);
		 client.setNom(name);
		 client.setPays(pays);
		 client.setTelephone(telephone);
		 client.setPrenom(prenom);
		 client.setEmail(email);
		 client.setUser(user);
	 clientRepository.save(client);
	 
	 String token=UUID.randomUUID().toString();
	 
	 //send confirmation token
	 ConfirmationToken confirmationToken = new ConfirmationToken();
	 confirmationToken.setCreatedAt(LocalDateTime.now());
	 confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
	 confirmationToken.setToken(token);
	 confirmationToken.setUser(user);
	// confirmationTokenRepository.save(confirmationToken);
	 
	 return "redirect:/login";
	 }
	
}
