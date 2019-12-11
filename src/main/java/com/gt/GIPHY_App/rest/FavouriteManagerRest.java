package com.gt.GIPHY_App.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gt.GIPHY_App.models.Favourite;
import com.gt.GIPHY_App.models.User;
import com.gt.GIPHY_App.repos.FavouriteRepository;
import com.gt.GIPHY_App.repos.UserRepository;

@RestController
public class FavouriteManagerRest {

	private FavouriteRepository favRepo;
	private UserRepository userRepo;
	
	@Autowired
	public FavouriteManagerRest(FavouriteRepository favRepo, UserRepository userRepo) {
		this.favRepo = favRepo;
		this.userRepo = userRepo;
	}
	
	@PostMapping("/removeFavourite")
	public ResponseEntity<String> removeFavourite(@RequestParam(name = "id") int id, HttpSession session) {
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
													.body("");
		}
		final Favourite favouriteForRemove = favRepo.findById(id).orElse(null);
		if (null != favouriteForRemove) {
			if (!user.equals(favouriteForRemove.getOwner())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			} else {
				favRepo.delete(favouriteForRemove);
			}
		}
		return ResponseEntity.ok().body("favourite with id: " + id 
										+ " is removed");
	}

	@GetMapping("/getFavourites")
	public ResponseEntity<List<Favourite>> getAllFavourites(HttpSession session) {
		final List<Favourite> favourites = new ArrayList<>();
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(favourites);
		} else {
			favourites.addAll(favRepo.findByOwner(user));
		}
		return ResponseEntity.ok(favourites);
	}

	@PostMapping("/addFavourite")
	public ResponseEntity<Favourite> addFavourite(
			@RequestParam(name = "url") String url,
			@RequestParam(name = "title") String title, 
			HttpSession session) {

		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		final Favourite favourite = new Favourite(url, title);
		favourite.setOwner(user);
		user.addFavourites(favourite);
		session.setAttribute("currentUser", userRepo.save(user));

		return ResponseEntity.ok(favourite);
	}
}
