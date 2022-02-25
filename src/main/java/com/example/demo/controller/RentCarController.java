package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Car;
import com.example.demo.entities.Client;
import com.example.demo.entities.Reservation;
import com.example.demo.repository.CarRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;

@Controller
@RequestMapping("/client")
public class RentCarController {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@GetMapping("/rentCar/{id}")
	public String rentCar(@PathVariable("id") int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Reservation reservation = new Reservation();
		reservation.setClient(clientRepository.findByUser(userRepository.findByUsername(auth.getName())));
		reservation.setCar(carRepository.getById(id));
		reservationRepository.save(reservation);
		return "redirect:/client/panier";
	}
	
	@GetMapping("/home")
	public  ModelAndView homeClient(){
		ModelAndView model = new ModelAndView("homeClient");
		System.out.println("homeClient**************");
		model.addObject("carsToRent",carRepository.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addObject("curentClient",clientRepository.findByUser(userRepository.findByUsername(auth.getName())));
		return model;
	}
}
