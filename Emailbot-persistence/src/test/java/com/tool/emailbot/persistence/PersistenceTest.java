// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence;

import com.tool.emailbot.persistence.inject.PersistentProducerTest;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Specifies the behavior of a persistence test.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@RunWith(Arquillian.class)
public abstract class PersistenceTest {

    @Inject
    protected EntityManager em;

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.tool.emailbot.persistence")
                .addPackage("com.tool.emailbot.persistence.aa")
                .addPackage("com.tool.emailbot.persistence.domain")
                .addPackage("com.tool.emailbot.persistence.validation")
                .addPackage("com.tool.emailbot.persistence.validation.impl")
                .addClass(PersistentProducerTest.class)
                .addAsLibraries(
                        DependencyResolvers.use(MavenDependencyResolver.class)
                                .artifact("org.jboss.slf4j:slf4j-jboss-logmanager:1.0.3.GA")
                                .artifact("com.google.guava:guava:13.0.1")
                                .resolveAsFiles())
                .addAsWebInfResource("jboss-ds.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return webArchive;
    }
}