package com.appreservas.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="country")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Country.findByFirstname",query = "select c from Country c where c.namecountry= ?1")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "namecountry",nullable = false, length = 50)
    private String namecountry;

}
