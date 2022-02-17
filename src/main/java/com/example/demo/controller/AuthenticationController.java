package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.CarRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class AuthenticationController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		model.addObject("users", userRepository.count());
		return model;
	}
	
	@RequestMapping("/register")
	public ModelAndView register() {
		ModelAndView model = new ModelAndView("register");
		return model;
	}
	
	@GetMapping({"/home","/"})
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		model.addObject("carsToRent",carRepository.findAll());
		return model;
	}
	
	@RequestMapping("/homeAdmin")
	public ModelAndView homeAdmin() {
		ModelAndView model = new ModelAndView("homeAdmin");
		return model;
	}
}
