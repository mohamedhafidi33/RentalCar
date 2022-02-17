package com.example.demo.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "nom")
	private String nom;

	@Column(name = "prenom")
	private String prenom;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="pays")
	private String pays;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name = "dateNaissance")
	private Date dateNaissance;
	
	@Email(message ="Must be a email valid")
	@NotNull(message="Email ne doit pas etre vide")
	private String email;
		
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "client")
	private List<Reservation> reservations ;
	
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
}
