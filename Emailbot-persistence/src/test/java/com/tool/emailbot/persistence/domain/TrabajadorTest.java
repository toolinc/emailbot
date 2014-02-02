/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.PersistenceTest;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import java.util.UUID;

import javax.persistence.Query;

/**
 *
 * @author edgar
 */
public class TrabajadorTest extends PersistenceTest{
    
    @Test
    public void createDependenciaAndPersona() {
        UUID uuid = UUID.randomUUID();
	Persona persona = new Persona();
	persona.setId(uuid);
	persona.setNombrePersona("Pepe");
	persona.setApellidoPaterno("Ramirez");
	persona.setApellidoMaterno("Nunez");
	GregorianCalendar fecha = new GregorianCalendar();
	fecha.set(Calendar.YEAR, 1990);
	fecha.set(Calendar.MONTH, 7);
	fecha.set(Calendar.DATE,26);
	persona.setFechaNacimiento(fecha.getTime());
	persona.setHomoclave("ORM");
	persona.setRfc("MARJ900726QR1");
	persona.setVersion(0);
        //Create a new Dependencia
        uuid = UUID.randomUUID();
        Dependencia dependencia = new Dependencia();
        dependencia.setId(uuid);
        dependencia.setActivo(true);
        dependencia.setVersion(0);
        dependencia.setAbreviacion("TD");
        dependencia.setName("New Test Dependecy to be used on Test");
	tx.begin();
        em.persist(dependencia);
        em.persist(persona);
        tx.commit();
    }
    
    @Test
    public void createTrabajador(){
	UUID uuid = UUID.randomUUID();
	
	Query queryPersona = em.createQuery("FROM Persona p WHERE p.apellidoMaterno = ?1");
        queryPersona.setParameter(1, "Nunez".toUpperCase());
        Persona persona = (Persona) queryPersona.getSingleResult();
	
	Query queryDependencia = em.createQuery("FROM Dependencia d WHERE d.abreviacion = ?1");
        queryDependencia.setParameter(1, "TD".toUpperCase());
        Dependencia dependencia = (Dependencia) queryDependencia.getSingleResult();
	
	Trabajador trabajador = new Trabajador();
	trabajador.setId(uuid);
	trabajador.setPersona(persona);
	trabajador.setDependencia(dependencia);
	trabajador.setNumeroTrabajador("012");
	trabajador.setSitucionLaboral(true);
	trabajador.setDirector(true);
	trabajador.setVersion(0);
    }
}
