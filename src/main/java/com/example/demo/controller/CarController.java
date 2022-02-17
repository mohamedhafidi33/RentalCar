package com.example.demo.controller;


import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entities.Car;
import com.example.demo.entities.Moteur;
import com.example.demo.entities.Vitesse;
import com.example.demo.repository.CarRepository;

@Controller
//@RequestMapping("/addCar")
public class CarController {
	@Autowired
	private CarRepository carRepository ;
	
	@GetMapping("addCarPage")
	ModelAndView getAddCarPage() {
		ModelAndView modelAndView= new ModelAndView("addCar");
		return modelAndView;
	}
	@PostMapping("addCar")
	public String addNewCar(
			@RequestParam("name")String name,
			@RequestParam("price")double price,
			@RequestParam("matricule")String matricule,
			@RequestParam("model")String model,
			@RequestParam("vitesse")Vitesse vitesse,
			@RequestParam("airCondition")boolean airCondition,
			@RequestParam("description")String description,
			@RequestParam("moteur")Moteur moteur,
			@RequestParam("image")byte[] image,
			@RequestParam("consommation")double consommation,
			@RequestParam("radar")boolean reculRadar,
			@RequestParam("dateRelease")Date dateRelease
			) {
		System.out.println("name "+name);
		Car car =new Car();
		car.setName(name);
		car.setDescription(description);
		car.setAirCondition(airCondition);
		car.setMatricule(matricule);
		car.setModel(model);
		car.setPrice(price);
		car.setVitesse(vitesse);
		car.setMoteur(moteur);
		car.setImage(image);
		car.setConsommation(consommation);
		car.setRadarRecul(reculRadar);
		car.setDateRelease(dateRelease);
		carRepository.save(car);
		return "redirect:/cars";
		
	}
	
	@GetMapping("/cars")
	public ModelAndView carsList() {
		ModelAndView model = new ModelAndView("listCars");
		model.addObject("carsList", carRepository.findAll());
		return model;
	}
	
	@PostMapping("edit/editCar")
	public ModelAndView editCar(@RequestParam("id")int id,
			@RequestParam("name")String name,
			@RequestParam("price")double price,
			@RequestParam("matricule")String matricule,
			@RequestParam("model")String model,
			@RequestParam("vitesse")Vitesse vitesse,
			@RequestParam("airCondition")boolean airCondition,
			@RequestParam("description")String description,
			@RequestParam("moteur")Moteur moteur,
			@RequestParam("image")byte[] image,
			@RequestParam("consommation")double consommation,
			@RequestParam("radar")boolean reculRadar,
			@RequestParam("dateRelease")Date dateRelease
			) {
		Car car =carRepository.getOne(id);
		car.setName(name);
		car.setDescription(description);
		car.setAirCondition(airCondition);
		car.setMatricule(matricule);
		car.setModel(model);
		car.setPrice(price);
		car.setVitesse(vitesse);
		car.setMoteur(moteur);
		//car.setImage(image);
		car.setConsommation(consommation);
		car.setRadarRecul(reculRadar);
		car.setDateRelease(dateRelease);
		carRepository.save(car);
		ModelAndView modelview = new ModelAndView("redirect:/cars");
		return modelview;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editCar(@PathVariable("id")int id) {
		ModelAndView model = new ModelAndView("editCar");
		model.addObject("car", carRepository.getOne(id));
		return model;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") int id) {
		carRepository.deleteById(id);
		return "redirect:/cars";
	}
	
	
}
