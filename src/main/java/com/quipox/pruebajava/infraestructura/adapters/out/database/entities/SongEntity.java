package com.quipox.pruebajava.infraestructura.adapters.out.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CANCION")
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "ARTISTA", nullable = false)
    private String artista;

    @Column(name = "ALBUM", nullable = false)
    private String album;

    @Column(name = "ANNO", nullable = false)
    private String anno;

    @Column(name = "GENERO", nullable = false)
    private String genero;

    @ManyToOne
    @JoinColumn(name = "LISTA_REPRODUCCION_ID", nullable = false)
    private PlayListEntity playList;

}
