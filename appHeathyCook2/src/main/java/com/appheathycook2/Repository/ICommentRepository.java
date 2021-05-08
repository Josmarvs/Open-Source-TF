package com.appheathycook2.Repository;

import com.appheathycook2.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment,Long> {

    @Query("Select co from Comment co where co.publication_Date=:publicDate")
    public List<Comment> find(@Param("publicDate") Date publication_Date);

}
