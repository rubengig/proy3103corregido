package com.proyecto.proyecto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.proyecto.entity.usuarioEntity;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.repository.usuarioRepository;

@Service
public class usuarioService implements serviceInterface<usuarioEntity>{

    @Autowired
    usuarioRepository oUsuarioRepository;

    @Autowired
    tipousuarioService oTipousuarioService;

     public Page<usuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            return oUsuarioRepository
                    .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
                            filter.get(), filter.get(), filter.get(), filter.get(),
                            oPageable);
        } else {
            return oUsuarioRepository.findAll(oPageable);
        }
    }

        public usuarioEntity get(Long id) {
        return oUsuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public Long count() {
        return oUsuarioRepository.count();
    }

    public Long delete(Long id) {
        oUsuarioRepository.deleteById(id);
        return 1L;
    }

    public usuarioEntity create(usuarioEntity oUsuarioEntity) {
        return oUsuarioRepository.save(oUsuarioEntity);
    }

    public usuarioEntity update(usuarioEntity oUsuarioEntity) {
        usuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();
        if (oUsuarioEntity.getNombre() != null) {
            oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
        }
        if (oUsuarioEntity.getApellido1() != null) {
            oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
        }
        if (oUsuarioEntity.getApellido2() != null) {
            oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
        }
        if (oUsuarioEntity.getEmail() != null) {
            oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
        }
        return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        oUsuarioRepository.deleteAll();
        return this.count();
    }

}
