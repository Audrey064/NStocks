package com.example.nstocks.entities;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public  class MenuDTO implements Serializable
{
    private int i ;
    private String description_url ;

}
