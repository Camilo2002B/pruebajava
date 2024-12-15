package com.quipox.pruebajava.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    private Long id;

    private String titulo;

    private String artista;

    private String album;

    private String anno;

    private String genero;

}
