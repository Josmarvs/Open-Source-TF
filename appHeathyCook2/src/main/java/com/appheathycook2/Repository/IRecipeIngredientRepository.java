package com.appheathycook2.Repository;

import com.appheathycook2.Entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IRecipeIngredientRepository extends JpaRepository<RecipeIngredient,Long>{
    public List<RecipeIngredient> findByQuantity(Integer quantity);
}
