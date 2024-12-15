package com.quipox.pruebajava.application.usecase;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.in.GetAllPlayListUseCase;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetAllPlayList implements GetAllPlayListUseCase {

    private final PlayListPort playListPort;

    @Override
    public List<PlayList> execute() {
        return playListPort.getAllPlayList();
    }
}
