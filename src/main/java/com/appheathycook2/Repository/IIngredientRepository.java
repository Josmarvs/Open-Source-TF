package com.appheathycook2.Repository;

import com.appheathycook2.Entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient,Long> {

    public List<Ingredient> findByName(String name);

}
