package com.quipox.pruebajava.infraestructura.ports.out;

import com.quipox.pruebajava.domain.PlayList;
import java.util.List;


public interface PlayListPort {

    PlayList save(PlayList playList);

    List<PlayList> getAllPlayList();

    List<PlayList> getPlayListByName(String listName);

    void deletePlayListByName(String listName);
}
