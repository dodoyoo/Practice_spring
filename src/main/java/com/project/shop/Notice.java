package com.project.shop;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String 글제목;
    @Column
    public Date 날짜;
}
