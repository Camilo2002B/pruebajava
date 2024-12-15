package com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongRequest {

    private Long id;

    @NotEmpty(message = "El título no puede estar vacío")
    private String titulo;

    @NotEmpty(message = "El artista no puede estar vacío")
    private String artista;

    @NotEmpty(message = "El álbum no puede estar vacío")
    private String album;

    @NotEmpty(message = "El año no puede estar vacío")
    private String anno;

    @NotEmpty(message = "El género no puede estar vacío")
    private String genero;
}
