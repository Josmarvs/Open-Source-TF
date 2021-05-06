package com.appreservas.Service;

import com.appreservas.Entity.Comment;

import java.util.Date;
import java.util.List;

public interface IIngredientService extends CrudService<Ingredient>{
    public List<Ingredient> find(Date publication_Date) throws Exception;

}
