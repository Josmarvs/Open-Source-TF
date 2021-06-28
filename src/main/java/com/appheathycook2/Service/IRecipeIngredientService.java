package com.appheathycook2.Service;

import com.appheathycook2.Entity.RecipeIngredient;

import java.util.List;

public interface IRecipeIngredientService extends CrudService<RecipeIngredient>{
    public List<RecipeIngredient> findByQuantity(Integer quantity)throws Exception;
}
