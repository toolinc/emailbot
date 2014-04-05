package com.tool.emailbot.adapter.jpa;

import com.tool.emailbot.common.domain.repository.Repository;
import com.tool.emailbot.domain.model.Trabajador;
import com.tool.emailbot.domain.repository.TrabajadorRepository;

import javax.inject.Inject;

/**
 * Created by edgar on 4/5/14.
 */
public class JpaTrabajadorRepository implements TrabajadorRepository {

    private final Repository<Trabajador> repository;

    @Inject
    public JpaTrabajadorRepository(Repository<Trabajador> repository) {
        this.repository = repository;
    }

    @Override
    public Trabajador findBy(String numeroTrabajador) {
        return null;
    }
}
