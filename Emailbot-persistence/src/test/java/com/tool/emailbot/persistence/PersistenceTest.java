/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tool.emailbot.persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author edgar
 */
public class PersistenceTest {

    private final static String PERSISTENCE_UNIT = "emailbotPUTest";
    protected static EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    @BeforeClass
    public static void createEMF() {
	if(emf == null){
	    emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
    }

    @AfterClass
    public static void closeEMF() {
	if(emf == null) emf.close();
    }

    @Before
    public void createEM() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEM() {
        em.close();
    }
}
