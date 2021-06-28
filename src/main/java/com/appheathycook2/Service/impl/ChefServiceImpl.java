package com.appheathycook2.Service.impl;

import com.appheathycook2.Entity.Beginner;
import com.appheathycook2.Entity.Chef;
import com.appheathycook2.Repository.IBeginnerRepository;
import com.appheathycook2.Repository.IChefRepository;
import com.appheathycook2.Service.IChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ChefServiceImpl implements IChefService {

    @Autowired
    private IChefRepository chefRepository;

    @Override
    @Transactional
    public Chef save(Chef chef) throws Exception {
        return chefRepository.save(chef);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        chefRepository.deleteById(id);
    }

    @Override
    public List<Chef> getAll() throws Exception {
        return chefRepository.findAll();
    }

    @Override
    public Optional<Chef> getById(Long id) throws Exception {
        return chefRepository.findById(id);
    }

    @Override
    public Chef findByDni(String dni) throws Exception {
        return chefRepository.findByDni(dni);
    }

    @Override
    public List<Chef> findByLastname(String lastname) throws Exception {
        return chefRepository.findByLastname(lastname);
    }

    @Override
    public List<Chef> findByFirstname(String firstname) throws Exception {
        return chefRepository.findByFirstname((firstname));
    }

    @Override
    public List<Chef> findByFirstnameAndLastname(String firstname, String lastname) throws Exception {
        return chefRepository.findByFirstnameAndLastname(firstname,lastname);
    }
}
