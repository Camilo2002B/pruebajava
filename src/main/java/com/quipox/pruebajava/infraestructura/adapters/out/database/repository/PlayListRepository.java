package com.quipox.pruebajava.infraestructura.adapters.out.database.repository;

import com.quipox.pruebajava.infraestructura.adapters.out.database.entities.PlayListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListRepository extends JpaRepository<PlayListEntity, Long>, JpaSpecificationExecutor<PlayListEntity> {

    List<PlayListEntity> findByNombreIgnoreCase(String nombre);
}
