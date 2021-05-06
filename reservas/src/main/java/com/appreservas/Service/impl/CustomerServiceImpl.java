package com.appreservas.Service.impl;

import com.appreservas.Entity.Customer;
import com.appreservas.Repository.ICustomerRepository;
import com.appreservas.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class CustomerServiceImpl implements ICustomerService {


    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) throws Exception {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        customerRepository.deleteById(id);

    }

    @Override
    public List<Customer> getAll() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) throws Exception {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByDni(String dni) throws Exception {
        return customerRepository.findByDni(dni);
    }

    @Override
    public List<Customer> findByLastname(String lastname) throws Exception {
        return customerRepository.findByLastname(lastname);
    }

    @Override
    public List<Customer> findByFirstnameAndLastname(String firstname, String lastname) throws Exception {
        return customerRepository.findByFirstnameAndLastname(firstname,lastname);
    }

    @Override
    public List<Customer> findByFirstname(String firstname) throws Exception {
        return customerRepository.findByFirstname(firstname);
    }
}
