package com.appreservas.Repository;

import com.appreservas.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient,Long> {

    @Query("Select i from Ingredient i where i.publication_Date=:publicDate")
    public List<Comment> find(@Param("publicDate") Date publication_Date);

}
