package com.quipox.pruebajava.application.usecase;

import com.quipox.pruebajava.infraestructura.ports.in.DeletePlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.out.PlayListPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeletePlayListByName implements DeletePlayListByNameUseCase {

    private final PlayListPort playListPort;

    @Override
    public void execute(String listName) {
        playListPort.deletePlayListByName(listName);
    }
}
