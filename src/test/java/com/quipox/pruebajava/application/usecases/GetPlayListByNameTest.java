package com.quipox.pruebajava.application.usecases;

import com.quipox.pruebajava.application.usecase.GetPlayListByName;
import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPlayListByNameTest {

    @Mock
    private PlayListPort playListPort;

    @InjectMocks
    private GetPlayListByName getPlayListByName;

    private List<PlayList> mockPlayLists;

    @BeforeEach
    void setUp() {
        mockPlayLists = Arrays.asList(
                new PlayList(1L, "list1", "description1", new ArrayList<>()),
                new PlayList(2L, "list2", "description2", new ArrayList<>())
        );
    }

    @Test
    void testExecute_ShouldReturnPlayLists_WhenValidNameProvided() {
        String listName = "list1";
        when(playListPort.getPlayListByName(listName)).thenReturn(mockPlayLists);

        List<PlayList> result = getPlayListByName.execute(listName);

        assertEquals(2, result.size());
        assertEquals("list1", result.get(0).getNombre());
        assertEquals("list2", result.get(1).getNombre());
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenNoPlayListsFound() {
        String listName = "nonExistingList";
        when(playListPort.getPlayListByName(listName)).thenReturn(Arrays.asList());

        List<PlayList> result = getPlayListByName.execute(listName);

        assertEquals(0, result.size());
    }
}
