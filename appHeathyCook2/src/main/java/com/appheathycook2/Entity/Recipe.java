package com.appheathycook2.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="description",nullable = true,length = 200)
    private String description;
    @Column(name = "publication_Date",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publication_Date;
    @Column(name = "assessment", nullable = true,length = 200)
    private String assessment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chef_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Chef chef;
}
