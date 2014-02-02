/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence.domain;

import com.tool.emailbot.persistence.PersistenceTest;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.persistence.Query;
import javax.persistence.RollbackException;


public class PersonaTest extends PersistenceTest{
    
    @Test
    public void createPersona() {
	UUID uuid = UUID.randomUUID();
	Persona persona = new Persona();
	persona.setId(uuid);
	persona.setNombrePersona("Jovas");
	persona.setApellidoPaterno("RICO");
	persona.setApellidoMaterno("Rico");
	GregorianCalendar fecha = new GregorianCalendar();
	fecha.set(Calendar.YEAR, 1990);
	fecha.set(Calendar.MONTH, 7);
	fecha.set(Calendar.DATE,26);
	persona.setFechaNacimiento(fecha.getTime());
	persona.setHomoclave("ORM");
	persona.setRfc("MARJ900726QR1");
	persona.setVersion(0);
	tx.begin();
        em.persist(persona);
        tx.commit();
    }
    
    @Test(expected = RollbackException.class)
    public void failToCreatePersona() {
        UUID uuid = UUID.randomUUID();
	Persona persona = new Persona();
	persona.setId(uuid);
	persona.setNombrePersona("Jovas");
	persona.setApellidoPaterno("RICO");
	persona.setApellidoMaterno("Rico");
	GregorianCalendar fecha = new GregorianCalendar();
	fecha.set(Calendar.YEAR, 1990);
	fecha.set(Calendar.MONTH, 7);
	fecha.set(Calendar.DATE,26);
	persona.setFechaNacimiento(fecha.getTime());
	persona.setHomoclave("ORM5");
	persona.setRfc("MARJ9007265343");
	persona.setVersion(0);
	tx.begin();
        em.persist(persona);
        tx.commit();
    }
    
    @Test
    public void updatePersona() {
	Query query = em.createQuery("FROM Persona p WHERE p.apellidoMaterno = ?1");
        query.setParameter(1, "RICO");
        Persona persona = (Persona) query.getSingleResult();
	tx.begin();
	persona.setNombrePersona("JOVANI");
        em.merge(persona);
        tx.commit();
    }  
}
