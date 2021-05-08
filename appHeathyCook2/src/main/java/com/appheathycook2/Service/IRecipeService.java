package com.appheathycook2.Service;

import com.appheathycook2.Entity.Recipe;

import java.util.Date;
import java.util.List;

public interface IRecipeService extends CrudService<Recipe> {
    public List<Recipe> find(Date publication_Date) throws Exception;

}
