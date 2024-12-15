package com.quipox.pruebajava.infraestructura.adapters.out.database;

import com.quipox.pruebajava.application.exception.PlayListNotFoundException;
import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.PlayListEntity;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.SongEntity;
import com.quipox.pruebajava.infraestructura.adapters.out.database.mapper.PlayListEntityMapper;
import com.quipox.pruebajava.infraestructura.adapters.out.database.repository.PlayListRepository;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PlayListAdapter implements PlayListPort {

    private final PlayListEntityMapper playListEntityMapper;

    private final PlayListRepository playListRepository;
    @Override
    public PlayList save(PlayList playList) {
        PlayListEntity playListEntity = playListEntityMapper.toEntity(playList);

        List<SongEntity> songEntities = playListEntityMapper.songListToSongEntityList(playList.getCanciones(), playListEntity);

        playListEntity.setCanciones(songEntities);

        playListEntity = playListRepository.save(playListEntity);

        return playListEntityMapper.toDomain(playListEntity);
    }

    @Override
    public List<PlayList> getAllPlayList() {
        List<PlayListEntity> playListEntities = playListRepository.findAll();
        return playListEntities.stream()
                .map(playListEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<PlayList> getPlayListByName(String listName) {
        List<PlayListEntity> playListEntities = playListRepository.findByNombreIgnoreCase(listName);
        if (playListEntities.isEmpty()) {
            throw new PlayListNotFoundException("No playlists found with the name: " + listName);
        }
        return playListEntities.stream()
                .map(playListEntityMapper::toDomain)
                .toList();
    }

    @Override
    public void deletePlayListByName(String listName) {
        List<PlayListEntity> playListEntities = playListRepository.findByNombreIgnoreCase(listName);
        if (playListEntities.isEmpty()) {
            throw new PlayListNotFoundException("No playlists found with the name: " + listName);
        }
        playListRepository.deleteAll(playListEntities);
    }
}
