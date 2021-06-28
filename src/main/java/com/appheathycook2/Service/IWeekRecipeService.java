package com.appheathycook2.Service;


import com.appheathycook2.Entity.Comment;
import com.appheathycook2.Entity.Recipe;
import com.appheathycook2.Entity.WeekRecipe;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IWeekRecipeService extends CrudService<WeekRecipe>{

    public List<WeekRecipe> find(Date added_date) throws Exception;


}
