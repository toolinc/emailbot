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
public class InformacionContactoTest extends PersistenceTest{
    
    @Test
    public void createInformacion(){
	
	UUID uuidpersona = UUID.randomUUID();
	Persona persona = new Persona();
	persona.setId(uuidpersona);
	persona.setNombrePersona("Aaron");
	persona.setApellidoPaterno("Martinez");
	persona.setApellidoMaterno("juarez");
	GregorianCalendar fecha = new GregorianCalendar();
	fecha.set(Calendar.YEAR, 1990);
	fecha.set(Calendar.MONTH, 7);
	fecha.set(Calendar.DATE, 26);
	persona.setFechaNacimiento(fecha.getTime());
	persona.setHomoclave("ORM");
	persona.setRfc("MARJ900726QR1");
	persona.setVersion(0);
	
	UUID uuid = UUID.randomUUID();
	
	InformacionContacto contacto = new InformacionContacto();
	contacto.setId(uuid);
	contacto.setPersona(persona);
	contacto.setEmail("jovanimtzrico@gmail.com");
	contacto.setTelefono("57120119");
	contacto.setExtension("55");
	contacto.setVersion(0);
	tx.begin();
	em.persist(persona);
	em.persist(contacto);
	tx.commit();
    }
    
    @Test
    public void updateInformacion(){
	Query query = em.createQuery("FROM InformacionContacto c WHERE c.telefono = ?1");
        query.setParameter(1, "57120119");
        InformacionContacto contacto = (InformacionContacto) query.getSingleResult();
	tx.begin();
	contacto.setTelefono("42100974");
        em.merge(contacto);
        tx.commit();
    }
}
