package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Car;
import com.example.demo.repository.CarRepository;

@Controller
public class RentCarController {
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("/carDetails/{id}")
	public ModelAndView carDetails(@PathVariable("id")int id) throws NotFoundException {
		ModelAndView model = new ModelAndView("carDetails");
		Car car=carRepository.findById(id).orElseThrow(()->new NotFoundException());
		System.out.println(car.getName());
		model.addObject("carDetailed",car);
		return model;
	}
}
