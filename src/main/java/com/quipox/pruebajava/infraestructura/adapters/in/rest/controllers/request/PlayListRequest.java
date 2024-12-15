package com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayListRequest {

    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotEmpty(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotEmpty(message = "La lista de canciones no puede estar vacía")
    private List<SongRequest> canciones;
}
