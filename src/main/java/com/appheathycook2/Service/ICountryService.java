package com.appheathycook2.Service;

import com.appheathycook2.Entity.Country;

import java.util.List;

public interface ICountryService extends CrudService<Country>{
    public List<Country> findByCountryName(String name)throws Exception;

}
