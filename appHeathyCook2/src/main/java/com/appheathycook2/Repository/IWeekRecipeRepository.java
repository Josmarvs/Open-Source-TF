package com.appheathycook2.Repository;



import com.appheathycook2.Entity.Comment;
import com.appheathycook2.Entity.WeekRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface IWeekRecipeRepository extends JpaRepository<WeekRecipe,Long> {
    @Query("Select wr from WeekRecipe wr where wr.added_date=:addedDate")
    public List<WeekRecipe> find(@Param("addedDate") Date added_date);

}
