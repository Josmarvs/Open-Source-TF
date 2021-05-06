package com.appreservas.Service.impl;

import com.appreservas.Entity.Country;
import com.appreservas.Repository.ICountryRepository;
import com.appreservas.Service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class CountryServiceImpl implements ICountryService {


    @Autowired
    private ICountryRepository countryRepository;

    @Override
    @Transactional
    public Country save(Country country) throws Exception {
        return countryRepository.save(country);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        countryRepository.deleteById(id);

    }

    @Override
    public List<Country> getAll() throws Exception {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> getById(Long id) throws Exception {
        return countryRepository.findById(id);
    }
    


    @Override
    public List<Country> findByFirstname(String namecountry) throws Exception {
        return countryRepository.findByFirstname(namecountry);
    }
}
