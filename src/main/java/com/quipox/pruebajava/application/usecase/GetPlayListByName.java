package com.quipox.pruebajava.application.usecase;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.ports.in.GetPlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class GetPlayListByName implements GetPlayListByNameUseCase {

    private final PlayListPort playListPort;

    @Override
    public List<PlayList> execute(String listName) {
        return playListPort.getPlayListByName(listName);
    }
}
