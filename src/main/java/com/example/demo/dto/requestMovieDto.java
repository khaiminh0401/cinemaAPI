package com.example.demo.dto;

import java.util.List;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Transient;

import com.example.demo.entity.Movie;

import lombok.Data;

@Data
@Entity
public class requestMovieDto extends Movie {
    @Transient
    private List<Integer> arrayLanguage;
    @Transient
    private List<String> arrayType;
    @Transient
    private List<String> arrayActor;
    @Transient
    private List<String> arrayDirector;
}
