package com.appreservas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Table(name="comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Description", nullable = true,length = 200)
    private String description;
    @Column(name = "publication_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date publication_Date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Chef_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Chef chef;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Beginner_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Beginner beginner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Recipe_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Recipe chef;


}
