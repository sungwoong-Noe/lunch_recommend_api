package com.lunch.lunch_recommend_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Test {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

}
