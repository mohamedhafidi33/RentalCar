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
		return model;
	}
	
	@GetMapping({"/home","/"})
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		System.out.println("home**************");
		model.addObject("carsToRent",carRepository.findAll());
		return model;
	}
	
	@RequestMapping("/admin/home")
	public ModelAndView homeAdmin() {
		ModelAndView model = new ModelAndView("homeAdmin");
		return model;
	}
}
