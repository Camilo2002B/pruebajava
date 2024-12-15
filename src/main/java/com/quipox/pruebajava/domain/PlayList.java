package com.quipox.pruebajava.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayList {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<Song> canciones;
}
