package com.appreservas.Repository;

import com.appreservas.Entity.Beginner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBeginnerRepository extends JpaRepository<Beginner, Long> {

    public Beginner findByDni(String dni);
    public List<Beginner> findByLastname(String lastname);
    public List<Beginner> findByFirstnameAndLastname(String firstname, String lastname);
    public List<Beginner> findByFirstname(String firstname);

}
