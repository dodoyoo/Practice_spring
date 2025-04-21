package com.project.shop;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String title;
    @Column()
    public Integer price;
}

