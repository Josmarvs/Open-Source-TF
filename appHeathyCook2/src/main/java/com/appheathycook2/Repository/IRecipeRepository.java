package com.appheathycook2.Repository;


import com.appheathycook2.Entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe,Long> {

    @Query("Select re from Recipe re where re.publication_Date=:publicDate")
    public List<Recipe> find(@Param("publicDate")Date publication_Date);
}
