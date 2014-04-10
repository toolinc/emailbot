/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Peticion;

/**
 *
 * @author edgar
 */
public interface PeticionRepository {
    void create(Peticion trabajador);

    Peticion update(Peticion peticion);

    void delete(Peticion trabajador);

    public Peticion findBy(String numeroTrabajador);
}
