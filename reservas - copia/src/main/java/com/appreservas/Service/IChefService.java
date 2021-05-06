package com.appreservas.Service;

import com.appreservas.Entity.Chef;

import java.util.List;


public interface IChefService extends CrudService<Chef>{

    public Chef findByDni(String dni) throws Exception;
    public List<Chef> findByLastname(String lastname) throws Exception;
    public List<Chef> findByFirstnameAndLastname( String firstname,String lastname)throws Exception;
    public List<Chef> findByFirstname(String firstname)throws Exception;

}
