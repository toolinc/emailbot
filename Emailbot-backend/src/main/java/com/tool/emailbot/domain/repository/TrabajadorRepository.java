package com.tool.emailbot.domain.repository;

import com.tool.emailbot.domain.model.Trabajador;

/**
 * Created by edgar on 4/5/14.
 */
public interface TrabajadorRepository {

    public Trabajador findBy(String numeroTrabajador);
}
