package com.appheathycook2.Service;

import com.appheathycook2.Entity.Ingredient;

import java.util.List;

public interface IIngredientService extends CrudService<Ingredient>{
    public List<Ingredient> findByName(String name)throws Exception;
}
