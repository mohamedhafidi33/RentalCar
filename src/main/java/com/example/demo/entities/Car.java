package com.example.demo.entities;


import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull(message = "La nom ne doit pas être vide")
	@NotEmpty(message = "La nom ne doit pas être vide")
	private String name;

	@Column
	private String matricule;
	
	@Column
	private String model;
	
	@Column
	private double consommation;

	@Column
	private double price;
	
	@Column
	private boolean radarRecul;
	
	@Column
	private Date dateRelease;
	
	@Column
	@NotNull(message = "On a besoin de savoir le type de vitesse")
	private Vitesse vitesse;
	
	@Column
	@NotNull(message = "On a besoin de savoir si il est climatisé")
	private boolean airCondition;

	@Column
	@NotNull(message = "La description ne doit pas être vide")
	private String description;
	
	@Lob
	@NotNull(message = "On a besoin d'au moins d'une image")
	private byte[] image;
	
	@Column
	private Moteur moteur;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "car")
	private List<Reservation> reservations ;
	
}
