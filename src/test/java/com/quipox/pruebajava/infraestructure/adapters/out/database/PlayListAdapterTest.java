package com.quipox.pruebajava.infraestructure.adapters.out.database;

import com.quipox.pruebajava.application.exception.PlayListNotFoundException;
import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.domain.Song;
import com.quipox.pruebajava.infraestructura.adapters.out.database.PlayListAdapter;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.PlayListEntity;
import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.SongEntity;
import com.quipox.pruebajava.infraestructura.adapters.out.database.mapper.PlayListEntityMapper;
import com.quipox.pruebajava.infraestructura.adapters.out.database.repository.PlayListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayListAdapterTest {

    @Mock
    private PlayListEntityMapper playListEntityMapper;

    @Mock
    private PlayListRepository playListRepository;

    @InjectMocks
    private PlayListAdapter playListAdapter;

    private PlayList playList;
    private PlayListEntity playListEntity;
    private List<SongEntity> songEntities;

    @BeforeEach
    void setUp() {
        playList = new PlayList(1L, "list1", "description1", List.of(new Song(1L, "song1", "song1", "song1", "song1", "song1")));

        playListEntity = new PlayListEntity(1L, "list1", "description1", new ArrayList<>()); // Lista vacía, se llenará después

        songEntities = List.of(
                new SongEntity(1L, "song1", "song1", "song1", "song1", "song1", playListEntity)
        );
    }

    @Test
    void testSave_ShouldSaveAndReturnPlayList() {
        when(playListEntityMapper.toEntity(playList)).thenReturn(playListEntity);
        when(playListEntityMapper.songListToSongEntityList(playList.getCanciones(), playListEntity)).thenReturn(songEntities);
        when(playListRepository.save(playListEntity)).thenReturn(playListEntity);
        when(playListEntityMapper.toDomain(playListEntity)).thenReturn(playList);

        PlayList result = playListAdapter.save(playList);

        verify(playListEntityMapper, times(1)).toEntity(playList);
        verify(playListEntityMapper, times(1)).songListToSongEntityList(playList.getCanciones(), playListEntity);
        verify(playListRepository, times(1)).save(playListEntity);
        verify(playListEntityMapper, times(1)).toDomain(playListEntity);

        assertEquals("list1", result.getNombre());
        assertEquals("description1", result.getDescripcion());
        assertEquals(1, result.getCanciones().size());
    }

    @Test
    void testGetAllPlayList() {
        when(playListRepository.findAll()).thenReturn(List.of(playListEntity)); // Simula el repositorio devolviendo la lista de PlayListEntity
        when(playListEntityMapper.toDomain(playListEntity)).thenReturn(playList); // Simula el mapper devolviendo PlayList

        List<PlayList> result = playListAdapter.getAllPlayList();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("list1", result.get(0).getNombre());
        assertEquals("description1", result.get(0).getDescripcion());

        verify(playListRepository).findAll();
        verify(playListEntityMapper).toDomain(playListEntity);
    }

    @Test
    void testGetPlayListByName_Found() {
        when(playListRepository.findByNombreIgnoreCase("list1")).thenReturn(List.of(playListEntity));
        when(playListEntityMapper.toDomain(playListEntity)).thenReturn(playList);

        List<PlayList> result = playListAdapter.getPlayListByName("list1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("list1", result.get(0).getNombre());
        assertEquals("description1", result.get(0).getDescripcion());

        verify(playListRepository).findByNombreIgnoreCase("list1");
        verify(playListEntityMapper).toDomain(playListEntity);
    }

    @Test
    void testGetPlayListByName_NotFound() {
        when(playListRepository.findByNombreIgnoreCase("nonexistent")).thenReturn(List.of());

        PlayListNotFoundException thrown = assertThrows(PlayListNotFoundException.class, () -> {
            playListAdapter.getPlayListByName("nonexistent");
        });

        assertEquals("No playlists found with the name: nonexistent", thrown.getMessage());

        verify(playListRepository).findByNombreIgnoreCase("nonexistent");
    }

    @Test
    void testDeletePlayListByName_Found() {
        when(playListRepository.findByNombreIgnoreCase("list1")).thenReturn(List.of(playListEntity));

        playListAdapter.deletePlayListByName("list1");

        verify(playListRepository).deleteAll(List.of(playListEntity));
    }

    @Test
    void testDeletePlayListByName_NotFound() {
        when(playListRepository.findByNombreIgnoreCase("nonexistent")).thenReturn(List.of());

        PlayListNotFoundException thrown = assertThrows(PlayListNotFoundException.class, () -> {
            playListAdapter.deletePlayListByName("nonexistent");
        });

        assertEquals("No playlists found with the name: nonexistent", thrown.getMessage());

        verify(playListRepository).findByNombreIgnoreCase("nonexistent");
    }

}
