package com.gt.GIPHY_App.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gt.GIPHY_App.models.Favourite;
import com.gt.GIPHY_App.models.User;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {
	public List<Favourite> findByOwner(final User owner);
}
