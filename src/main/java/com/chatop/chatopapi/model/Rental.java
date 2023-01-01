package com.chatop.chatopapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

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

    @CreationTimestamp
    private Date updated_at;

}
