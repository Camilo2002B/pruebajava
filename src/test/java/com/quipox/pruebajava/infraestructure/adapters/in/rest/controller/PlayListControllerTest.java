package com.quipox.pruebajava.infraestructure.adapters.in.rest.controller;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.domain.Song;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.PlayListController;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request.PlayListRequest;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request.SongRequest;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.response.PlayListResponse;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.mappers.PlayListMapper;
import com.quipox.pruebajava.infraestructura.ports.in.DeletePlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.GetAllPlayListUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.GetPlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.SavePlayListUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayListControllerTest {

    @Mock
    private PlayListMapper mapper;

    @Mock
    private GetAllPlayListUseCase getAllPlayListUseCase;

    @Mock
    private SavePlayListUseCase savePlayListUseCase;

    @Mock
    private GetPlayListByNameUseCase getPlayListByNameUseCase;

    @Mock
    private DeletePlayListByNameUseCase deletePlayListByNameUseCase;

    @InjectMocks
    private PlayListController playListController;

    private PlayList playList;
    private PlayListResponse playListResponse;

    @BeforeEach
    void setUp() {
        playList = new PlayList(1L, "list1", "description1", List.of(new Song(1L, "song1", "song1", "song1", "song1", "song1")));
        playListResponse = new PlayListResponse(1L, "list1", "description1", new ArrayList<>());
    }

    @Test
    void testGetAllPlaylists() {
        when(getAllPlayListUseCase.execute()).thenReturn(List.of(playList));
        when(mapper.toResponse(playList)).thenReturn(playListResponse);

        ResponseEntity<List<PlayListResponse>> result = playListController.getAllPlaylists();

        assertNotNull(result);

        verify(getAllPlayListUseCase).execute();

        verify(mapper).toResponse(playList);
    }

    @Test
    void testGetPlaylistsByName() {
        String listName = "list1";
        PlayList playList = new PlayList(1L, "list1", "description1",
                List.of(new Song(1L, "song1", "song1", "song1", "song1", "song1")));
        PlayListResponse playListResponse = new PlayListResponse(1L, "list1", "description1", new ArrayList<>());

        when(getPlayListByNameUseCase.execute(listName)).thenReturn(List.of(playList));
        when(mapper.toResponse(playList)).thenReturn(playListResponse);

        ResponseEntity<List<PlayListResponse>> result = playListController.getPlaylistsByName(listName);

        assertNotNull(result);
        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals("list1", result.getBody().get(0).getNombre());
        assertEquals("description1", result.getBody().get(0).getDescripcion());

        verify(getPlayListByNameUseCase).execute(listName);

        verify(mapper).toResponse(playList);
    }

    @Test
    void testDeletePlaylistsByName() {
        String listName = "list1";

        doNothing().when(deletePlayListByNameUseCase).execute(listName);

        playListController.deletePlaylistsByName(listName);

        verify(deletePlayListByNameUseCase).execute(listName);
    }


}
