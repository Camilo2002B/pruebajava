package com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request.PlayListRequest;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.response.PlayListResponse;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.mappers.PlayListMapper;
import com.quipox.pruebajava.infraestructura.ports.in.DeletePlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.GetAllPlayListUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.GetPlayListByNameUseCase;
import com.quipox.pruebajava.infraestructura.ports.in.SavePlayListUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/list")
@CrossOrigin( origins = "http://localhost:4200")
public class PlayListController {

    private final PlayListMapper mapper;

    private final GetAllPlayListUseCase getAllPlayListUseCase;

    private final SavePlayListUseCase savePlayListUseCase;

    private final GetPlayListByNameUseCase getPlayListByNameUseCase;

    private final DeletePlayListByNameUseCase deletePlayListByNameUseCase;

    @GetMapping
    public ResponseEntity<List<PlayListResponse>> getAllPlaylists() {
        List<PlayList> response = getAllPlayListUseCase.execute();
        return new ResponseEntity<>(response.stream().map(mapper::toResponse).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PlayListResponse> savePlayList(@RequestBody @Valid PlayListRequest request)  {
        PlayList response = savePlayListUseCase.execute(mapper.toDomain(request));
        return new ResponseEntity<>(mapper.toResponse(response), HttpStatus.OK);
    }

    @GetMapping("/{listName}")
    public ResponseEntity<List<PlayListResponse>> getPlaylistsByName(@PathVariable String listName) {
        List<PlayList> response = getPlayListByNameUseCase.execute(listName);
        return new ResponseEntity<>(response.stream().map(mapper::toResponse).toList(), HttpStatus.OK);
    }

    @DeleteMapping("/{listName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePlaylistsByName(@PathVariable String listName) {
        deletePlayListByNameUseCase.execute(listName);
    }
}
