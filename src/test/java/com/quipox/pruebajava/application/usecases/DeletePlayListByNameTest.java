package com.quipox.pruebajava.application.usecases;

import com.quipox.pruebajava.application.usecase.DeletePlayListByName;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeletePlayListByNameTest {

    @Mock
    private PlayListPort playListPort;

    @InjectMocks
    private DeletePlayListByName deletePlayListByName;

    @Test
    void shouldCallDeletePlayListByName() {
        String listName = "TestPlaylist";

        deletePlayListByName.execute(listName);

        Mockito.verify(playListPort, Mockito.times(1)).deletePlayListByName(listName);
    }
}
