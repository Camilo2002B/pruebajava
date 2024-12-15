package com.quipox.pruebajava.application.usecases;

import com.quipox.pruebajava.application.usecase.GetAllPlayList;
import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllPlayListTest {

    @Mock
    private PlayListPort playListPort;

    @InjectMocks
    private GetAllPlayList getAllPlayList;

    @Test
    void testExecute() {
        PlayList playList1 = new PlayList(1L, "Playlist 1", "Description 1", new ArrayList<>());
        PlayList playList2 = new PlayList(2L, "Playlist 2", "Description 2", new ArrayList<>());
        List<PlayList> mockPlayList = Arrays.asList(playList1, playList2);

        when(playListPort.getAllPlayList()).thenReturn(mockPlayList);

        List<PlayList> result = getAllPlayList.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Playlist 1", result.get(0).getNombre());
        assertEquals("Playlist 2", result.get(1).getNombre());

        verify(playListPort, times(1)).getAllPlayList();
    }
}
