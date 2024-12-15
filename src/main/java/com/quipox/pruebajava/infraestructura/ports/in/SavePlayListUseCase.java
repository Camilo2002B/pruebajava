package com.quipox.pruebajava.infraestructura.ports.in;

import com.quipox.pruebajava.domain.PlayList;

public interface SavePlayListUseCase {

    PlayList execute(PlayList playList);
}
