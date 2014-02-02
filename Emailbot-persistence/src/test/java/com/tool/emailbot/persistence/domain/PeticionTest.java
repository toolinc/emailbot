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

/**
 *
 * @author edgar
 */
public class PeticionTest extends PersistenceTest{
   
    @Test
    public void createPersona(){
	
    }
    
    @Test
    public void createPeticion(){
	UUID uuid = UUID.randomUUID();
	Persona persona = new Persona();
	persona.setId(uuid);
	persona.setNombrePersona("Jovani");
	persona.setApellidoPaterno("Martinez");
	persona.setApellidoMaterno("Martinez");
	GregorianCalendar fecha = new GregorianCalendar();
	fecha.set(Calendar.YEAR, 1990);
	fecha.set(Calendar.MONTH, 7);
	fecha.set(Calendar.DATE, 26);
	persona.setFechaNacimiento(fecha.getTime());
	persona.setHomoclave("ORM");
	persona.setRfc("MARJ900726QR1");
	persona.setVersion(0);
	
	UUID uuid1 = UUID.randomUUID();
	Estatus estatus = Estatus.CREADA;
	
	Peticion peticion = new Peticion();
	peticion.setId(uuid1);
	peticion.setPersona(persona);
	peticion.setEstatus(estatus);
	peticion.setEmail("jovanimtzrico@gmail.com");
	peticion.setUsername("Jovas");
	peticion.setVersion(0);
	tx.begin();
	em.persist(persona);
	em.persist(peticion);
	tx.commit();
    }
    
    //@Test
    public void updatePeticion(){
	Query query = em.createQuery("FROM Peticion p WHERE p.username = ?1");
	query.setParameter(1, "Jovani".toUpperCase());
	Peticion peticion = (Peticion) query.getSingleResult();
	
	peticion.setEmail("jovani.martinez.rico@gmail.com");
	tx.begin();
	em.merge(peticion);
	tx.commit();
    }
}
