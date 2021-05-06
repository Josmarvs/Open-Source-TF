package com.appreservas.Service.impl;

import com.appreservas.Entity.Comment;
import com.appreservas.Repository.ICommentRepository;
import com.appreservas.Service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        return IngredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Recipe> getAll() throws Exception {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> getById(Long id) throws Exception {
        return IngredientRepository.findById(id);
    }

}
