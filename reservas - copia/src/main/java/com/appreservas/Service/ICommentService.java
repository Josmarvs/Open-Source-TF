package com.appreservas.Service;

import com.appreservas.Entity.Comment;

import java.util.Date;
import java.util.List;

public interface ICommentService extends CrudService<Comment>{
    public List<Comment> find(Date publication_Date) throws Exception;

}
