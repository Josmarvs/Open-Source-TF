package com.appreservas.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = true,length = 200)
    private String description;
    @Column(name = "number_person", nullable = false)
    private int numberPerson;
    @Column(name = "create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "checkin_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkin_Date;
    @Column(name = "checkout_Date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkout_Date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Customer customer;

}
