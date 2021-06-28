package com.appheathycook2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name= "weekRecipe")
public class WeekRecipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "added_date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date added_date;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beginner_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Beginner beginner;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Recipe recipe;
}
