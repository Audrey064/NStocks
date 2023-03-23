package com.example.nstocks.entities.dtos;

import lombok.Data;

@Data
public class ArticleDTO {
    private String ref_id ;
    private String name ;
    private String category;
    private double price ;
}
