package com.appheathycook2.Service.impl;

import com.appheathycook2.Entity.Ingredient;
import com.appheathycook2.Repository.IIngredientRepository;
import com.appheathycook2.Service.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class IngredientServiceImpl implements IIngredientService {

    @Autowired
    private IIngredientRepository ingredientRepository;

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient) throws Exception {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void delete(Long id) throws Exception {
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAll() throws Exception {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> getById(Long id) throws Exception {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> findByName(String name) throws Exception {
        return ingredientRepository.findByName(name);
    }
}
