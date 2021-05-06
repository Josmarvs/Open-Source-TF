package com.appreservas.Service;

import com.appreservas.Entity.Comment;

import java.util.Date;
import java.util.List;

public interface IRecipeService extends CrudService<Recipe>{
    public List<Recipe> find(Date publication_Date) throws Exception;

}
