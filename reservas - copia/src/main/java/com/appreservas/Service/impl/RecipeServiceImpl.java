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
public class RecipeServiceImpl implements IRecipeService {

    @Autowired
    private IRecipeRepository recipeRepository;

    @Override
    @Transactional
    public Recipe save(Recipe recipe) throws Exception {
        return RecipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> getAll() throws Exception {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> getById(Long id) throws Exception {
        return recipeRepository.findById(id);
    }

    @Override
    public List<Recipe> find(Date publication_Date) throws Exception {
        return recipeRepository.find(publication_Date);
    }
}
