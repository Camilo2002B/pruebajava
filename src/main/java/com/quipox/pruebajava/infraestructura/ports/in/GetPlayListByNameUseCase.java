package com.quipox.pruebajava.infraestructura.ports.in;

import com.quipox.pruebajava.domain.PlayList;

import java.util.List;

public interface GetPlayListByNameUseCase {

    List<PlayList> execute(String listName);
}
