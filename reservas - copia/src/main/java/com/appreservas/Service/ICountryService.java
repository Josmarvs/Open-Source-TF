package com.appreservas.Service;

import com.appreservas.Entity.Country;

import java.util.List;


public interface ICountryService extends CrudService<Country>{


    public List<Country> findByFirstname(String namecountry)throws Exception;

}
