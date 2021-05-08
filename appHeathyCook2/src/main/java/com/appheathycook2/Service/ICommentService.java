package com.appheathycook2.Service;

import com.appheathycook2.Entity.Comment;

import java.util.Date;
import java.util.List;

public interface ICommentService extends CrudService<Comment> {
    public List<Comment> find(Date publication_Date) throws Exception;

}
