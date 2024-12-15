package com.quipox.pruebajava.infraestructura.adapters.out.database.mapper;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.domain.Song;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.PlayListEntity;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.SongEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PlayListEntityMapper {

    PlayList toDomain(PlayListEntity entity);

    PlayListEntity toEntity(PlayList domain);

    default List<SongEntity> songListToSongEntityList(List<Song> songs, PlayListEntity playListEntity) {
        if (songs == null) {
            return null;
        }

        List<SongEntity> songEntities = new ArrayList<>();
        for (Song song : songs) {
            SongEntity songEntity = new SongEntity();
            songEntity.setTitulo(song.getTitulo());
            songEntity.setArtista(song.getArtista());
            songEntity.setAlbum(song.getAlbum());
            songEntity.setAnno(song.getAnno());
            songEntity.setGenero(song.getGenero());
            songEntity.setPlayList(playListEntity); // Asociamos la lista de reproducción
            songEntities.add(songEntity);
        }
        return songEntities;
    }
}
