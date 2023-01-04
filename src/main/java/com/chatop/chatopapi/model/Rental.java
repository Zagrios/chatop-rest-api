package com.chatop.chatopapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rentals")
@Data

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float surface;
    private float price;
    private String picture;
    private String description;
    private Long owner_id;

    @CreationTimestamp
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "rental_id")
    List<Message> messages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;



}
