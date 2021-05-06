package com.appreservas.Service;

import com.appreservas.Entity.Customer;

import java.util.List;


public interface ICustomerService extends CrudService<Customer>{

    public Customer findByDni(String dni) throws Exception;
    public List<Customer> findByLastname(String lastname) throws Exception;
    public List<Customer> findByFirstnameAndLastname( String firstname,String lastname)throws Exception;
    public List<Customer> findByFirstname(String firstname)throws Exception;

}
