package com.appheathycook2.Service;


import com.appheathycook2.Entity.Recipe;
import com.appheathycook2.Entity.WeekRecipe;

import java.util.Date;
import java.util.List;

public interface IWeekRecipeService extends CrudService<WeekRecipe>{
    public List<WeekRecipe> find(Date weekRecipe) throws Exception;
}
