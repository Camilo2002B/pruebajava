package com.quipox.pruebajava.application.usecases;

import com.quipox.pruebajava.application.usecase.SavePlayList;
import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePlayListTest {

    @Mock
    private PlayListPort playListPort;

    @InjectMocks
    private SavePlayList savePlayList;

    private PlayList playList;

    @BeforeEach
    void setUp() {
        playList = new PlayList(1L, "list1", "description1", new ArrayList<>());
    }

    @Test
    void testExecute_ShouldSaveAndReturnPlayList() {
        when(playListPort.save(playList)).thenReturn(playList);

        PlayList result = savePlayList.execute(playList);

        assertEquals("list1", result.getNombre());
        assertEquals("description1", result.getDescripcion());
    }
}
