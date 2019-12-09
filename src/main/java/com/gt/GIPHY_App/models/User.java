package com.gt.GIPHY_App.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="User")
@JsonIgnoreProperties("password")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "Username", nullable = false)
	private String username;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
	@Column(name = "Email", nullable = false, unique = true)
	private String email;
	
	@OneToMany(mappedBy="owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Favourite> favourites;
	
	public User() {
		
	}
	
	public User(final String username, final String password, final String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Favourite> getFavourites() {
		if(null == favourites) {
			favourites = new ArrayList<>();
		}
		return favourites;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}
	
	public void addFavourites(Favourite favourite) {
		getFavourites().add(favourite);
	}
	
	
}
