package com.appreservas.Repository;

import com.appreservas.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe,Long> {

    @Query("Select r from Recipe r where r.publication_Date=:publicDate")
    public List<Comment> find(@Param("publicDate") Date publication_Date);

}
