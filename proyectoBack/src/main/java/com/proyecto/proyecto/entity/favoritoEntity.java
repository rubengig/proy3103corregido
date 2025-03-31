package com.proyecto.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "favoritos")
public class favoritoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "articulo")
    private articuloEntity articulo;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "usuario")
    private usuarioEntity usuario;

    public favoritoEntity() {
    }

    public favoritoEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public articuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(articuloEntity articulo) {
        this.articulo = articulo;
    }

    public usuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(usuarioEntity usuario) {
        this.usuario = usuario;
    }

    

}
