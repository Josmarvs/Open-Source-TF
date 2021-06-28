package com.appheathycook2.Service.impl;

import com.appheathycook2.Entity.Ingredient;
import com.appheathycook2.Entity.RecipeIngredient;
import com.appheathycook2.Repository.IIngredientRepository;
import com.appheathycook2.Repository.IRecipeIngredientRepository;
import com.appheathycook2.Service.IRecipeIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RecipeIngredientServiceImpl implements IRecipeIngredientService {

    @Autowired
    private IRecipeIngredientRepository recipeIngredientRepository;

    @Override
    @Transactional
    public RecipeIngredient save(RecipeIngredient recipeIngredient) throws Exception {
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Override
    public void delete(Long id) throws Exception {
        recipeIngredientRepository.deleteById(id);
    }

    @Override
    public List<RecipeIngredient> getAll() throws Exception {
        return recipeIngredientRepository.findAll();
    }

    @Override
    public Optional<RecipeIngredient> getById(Long id) throws Exception {
        return recipeIngredientRepository.findById(id);
    }

    @Override
    public List<RecipeIngredient> findByQuantity(Integer quantity) throws Exception {
        return recipeIngredientRepository.findByQuantity(quantity);
    }

}
