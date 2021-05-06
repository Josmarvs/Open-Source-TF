package com.appreservas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="beginner")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Beginner.findByFirstname",query = "select b from Beginner b where b.firstname= ?1")
public class Beginner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname",nullable = false, length = 50)
    private String firstname;
    @Column(name = "lastname",nullable = false, length = 50)
    private String lastname;
    @Column(name = "dni",nullable = true, length = 8)
    private String dni;
    @Column(name = "Address",nullable = true, length = 150)
    private String Address;
    @Column(name = "phone",nullable = true, length = 9)
    private String phone;
    @Column(name = "email",nullable = true, length = 50)
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Country country;

}
