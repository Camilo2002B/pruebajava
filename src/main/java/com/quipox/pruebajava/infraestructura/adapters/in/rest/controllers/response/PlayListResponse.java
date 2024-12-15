package com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayListResponse {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<SongResponse> canciones;
}
