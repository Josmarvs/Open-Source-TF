package com.appheathycook2.Repository;

import com.appheathycook2.Entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChefRepository extends JpaRepository<Chef,Long> {
    public Chef findByDni(String dni);
    public List<Chef> findByLastname(String lastname);
    public List<Chef> findByFirstname(String firstname);
    public List<Chef> findByFirstnameAndLastname(String firstname, String lastname);

}
