package com.appheathycook2.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description",nullable = true,length = 200)
    private String description;
    @Column(name = "publication_Date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publication_Date;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "chef_id",nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    //private Chef chef;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beginner_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Beginner beginner;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "recipe_id",nullable = false)
    //@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    //private Recipe recipe;

}
