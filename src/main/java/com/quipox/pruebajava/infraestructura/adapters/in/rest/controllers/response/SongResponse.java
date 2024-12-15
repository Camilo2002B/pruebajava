package com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongResponse {

    private Long id;

    private String titulo;

    private String artista;

    private String album;

    private String anno;

    private String genero;
}
