package com.appheathycook2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

@NamedQuery(name = "Ingredient.findByName", query = "select ing from Ingredient ing where ing.name= ?1")
public class Ingredient implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description",nullable = false,length = 200)
    private String description;
    @Column(name = "name",nullable = false,length = 50)
    private String name;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.LAZY)
    private Set<Recipe> recipes = new HashSet<>();

}
