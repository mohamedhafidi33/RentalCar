package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	
}
