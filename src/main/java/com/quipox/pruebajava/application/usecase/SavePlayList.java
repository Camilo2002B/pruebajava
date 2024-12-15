package com.quipox.pruebajava.application.usecase;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.in.SavePlayListUseCase;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SavePlayList implements SavePlayListUseCase {

    private final PlayListPort playListPort;

    @Override
    public PlayList execute(PlayList playList) {
        return playListPort.save(playList);
    }
}
