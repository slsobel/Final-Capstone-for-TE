package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Ingredient;

@Service
public class IngredientSqlDAO implements IngredientDAO
{
	private JdbcTemplate jdbcTemplate;
	
	public IngredientSqlDAO(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Ingredient> getAll()
	{
		List<Ingredient> ingredients = new ArrayList<>();
		
		
		
	}
	
	public Ingredient getById(int id)
	{
		String sql = "SELECT recipe_id" + 
					", ingredient_id" + 
					", ingredient_name" + 
					", category_id" + 
					" FROM ingredients" + 
					" WHERE ingredient_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next())
		{
			return mapRowToIngredient(results);
		}
		return null;
	}
	
	
	public Ingredient create(Ingredient newIngredient)
	{
		// confirm sql
		String sql = "INSERT INTO ingredients" + 
					"(" + 
					"recipe_id" + 
					", ingredient_name" + 
					", category_id" + 
					")" + 
					" VALUES" + 
					"(?, ?, ?)" + 
					" RETURNING ingredient_id;";
		
		Integer id = jdbcTemplate.queryForObject(sql, Integer.class, 
												newIngredient.getrecipeId(),
												newIngredient.getIngredientName(),
												newIngredient.getCategoryId());
		return getById(id);
	}
	
	
	private Ingredient mapRowToIngredient(SqlRowSet rowSet)
	{
		Ingredient ingredient = new Ingredient();
		ingredient.setRecipeId(rowSet.getInt("recipe_id"));
		ingredient.setIngredientId(rowSet.getInt("ingredient_id"));
		ingredient.setIngredientName(rowSet.getString("ingredient_name"));
		ingredient.setCategoryId(rowSet.getInt("category_id"));
		
		return ingredient;
	}
	
}
