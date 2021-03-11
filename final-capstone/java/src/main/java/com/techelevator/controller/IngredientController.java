package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.IngredientDAO;
import com.techelevator.model.Ingredient;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class IngredientController
{
	@Autowired
	private IngredientDAO ingredientDAO;
	
	public IngredientController(IngredientDAO ingredientDAO)
	{
		this.ingredientDAO = ingredientDAO;
	}
	
	@RequestMapping(path = "/ingredientList", method = RequestMethod.GET)
	public List<Ingredient> listRecipes()
	{
		
	}
	
	@RequestMapping(path = "/ingredientList/{ingredient_id}", method = RequestMethod.GET)
	public Ingredient getIngredientById(@PathVariable int ingredient_id)
	{
		return ingredientDAO.getById(ingredient_id);
	}
	
	
	@RequestMapping(path = "/ingredientList", method = RequestMethod.POST)
	public Ingredient createIngredient(@RequestBody Ingredient ingredient)
	{
		return ingredientDAO.create(ingredient);
	}
}
