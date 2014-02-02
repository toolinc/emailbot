/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.PersistenceTest;

import org.junit.Test;

import java.util.UUID;

import javax.persistence.Query;

public class DependenciaTest extends PersistenceTest {

    @Test
    public void createNewDependency() {
	UUID uuid = UUID.randomUUID();
	Dependencia dependencia = new Dependencia();
	dependencia.setId(uuid);
	dependencia.setActivo(true);
	dependencia.setVersion(0);
	dependencia.setAbreviacion("CEM Polanco");
	dependencia.setName("Centro de Extension Polanco");
	tx.begin();
	em.persist(dependencia);
	tx.commit();
    }

    @Test
    public void createOtherDependency() {
	UUID uuid = UUID.randomUUID();
	Dependencia dependencia = new Dependencia();
	dependencia.setId(uuid);
	dependencia.setActivo(true);
	dependencia.setVersion(1);
	dependencia.setAbreviacion("CEM Nuevo Leon");
	dependencia.setName("Centro de Extension Nuevo Leon");
	tx.begin();
	em.persist(dependencia);
	tx.commit();
    }

    @Test
    public void updateNewDependency() {
	Query query = em.createQuery("FROM Dependencia d WHERE d.abreviacion = ?1");
	query.setParameter(1, "cem polanco".toUpperCase());
	Dependencia dependencia = (Dependencia) query.getSingleResult();
	dependencia.setName("New");
	
	Query query1 = em.createQuery("FROM Dependencia d WHERE d.abreviacion = ?1");
	query1.setParameter(1, "cem Nuevo Leon".toUpperCase());
	Dependencia dependencia1 = (Dependencia) query1.getSingleResult();
	
	tx.begin();
	em.remove(dependencia1);
	em.merge(dependencia);
	tx.commit();
    }
    
    //@Test
    public void deleteDependency() {
	Query query = em.createQuery("FROM Dependencia d WHERE d.abreviacion = ?1");
	query.setParameter(1, "cem Nuevo Leon".toUpperCase());
	Dependencia dependencia = (Dependencia) query.getSingleResult();
	tx.begin();
	em.remove(dependencia);
	tx.commit();
    }
}
