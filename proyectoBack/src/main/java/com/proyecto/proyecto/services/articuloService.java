package com.proyecto.proyecto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.entity.articuloEntity;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.repository.articuloRepository;

@Service
public class articuloService implements serviceInterface<articuloEntity>{

    @Autowired
    articuloRepository oArticuloRepository;

    public Page<articuloEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oArticuloRepository.findByNombreContainingOrDescripcionContaining(filter.get(), filter.get(), oPageable);
        } else {
            return oArticuloRepository.findAll(oPageable);
        }
    }

    public articuloEntity get(Long id) {
        return oArticuloRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado"));
    }

    public Long count() {
        return oArticuloRepository.count();
    }

    public Long delete(Long id) {
        oArticuloRepository.deleteById(id);
        return 1L;
    }

    public articuloEntity create(articuloEntity oArticuloEntity) {
        return oArticuloRepository.save(oArticuloEntity);
    }

    public articuloEntity update(articuloEntity oArticuloEntity) {
        articuloEntity oArticuloEntityFromDatabase = oArticuloRepository.findById(oArticuloEntity.getId()).get();
        if (oArticuloEntity.getNombre() != null) {
            oArticuloEntityFromDatabase.setNombre(oArticuloEntity.getNombre());
        }
        if (Double.compare(oArticuloEntity.getPrecio(), Double.NaN) != 0) {
            oArticuloEntityFromDatabase.setPrecio(oArticuloEntity.getPrecio());
        }
        return oArticuloRepository.save(oArticuloEntityFromDatabase);
    }

    public Long deleteAll() {
        oArticuloRepository.deleteAll();
        return this.count();
    }
    
}
