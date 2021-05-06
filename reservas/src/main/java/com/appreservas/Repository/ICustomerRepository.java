package com.appreservas.Repository;

import com.appreservas.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByDni(String dni);
    public List<Customer> findByLastname(String lastname);
    public List<Customer> findByFirstnameAndLastname( String firstname,String lastname);
    public List<Customer> findByFirstname(String firstname);

}
