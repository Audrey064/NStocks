package com.example.nstocks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table (name = "articles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article
{
    @Id
    private String ref_id ;
    private String name ;
    private String category;
    private double price ;
}
