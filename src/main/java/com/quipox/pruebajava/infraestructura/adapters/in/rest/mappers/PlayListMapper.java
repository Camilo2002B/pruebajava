package com.quipox.pruebajava.infraestructura.adapters.in.rest.mappers;

import com.quipox.pruebajava.domain.PlayList;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.request.PlayListRequest;
import com.quipox.pruebajava.infraestructura.adapters.in.rest.controllers.response.PlayListResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayListMapper {

    PlayList toDomain(PlayListRequest request);

    PlayListResponse toResponse(PlayList domain);
}
