package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Car;
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
	
	
	// I know it shouldn't be here but it's Okay
	@GetMapping("/carDetails/{id}")
	public ModelAndView carDetails(@PathVariable("id")int id) throws NotFoundException {
		ModelAndView model = new ModelAndView("carDetails");
		Car car=carRepository.findById(id).orElseThrow(()->new NotFoundException());
		System.out.println(car.getName());
		model.addObject("carDetailed",car);
		return model;
	}
}
