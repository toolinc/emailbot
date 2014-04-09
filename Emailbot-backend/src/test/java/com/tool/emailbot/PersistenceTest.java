// Copyright 2014 Tool Inc.

package com.tool.emailbot;

import com.tool.emailbot.infrastructure.cdi.EmailbotModuleTest;

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
                .addPackage("com.tool.emailbot")
                .addPackage("com.tool.emailbot.domain")
                .addPackage("com.tool.emailbot.domain.model")
                .addPackage("com.tool.emailbot.domain.repository")
		.addPackage("com.tool.emailbot.domain.model")
                .addClass(EmailbotModuleTest.class)
                .addAsLibraries(
                        DependencyResolvers.use(MavenDependencyResolver.class)
                                .goOffline()
                                .artifact("org.jboss.slf4j:slf4j-jboss-logmanager:1.0.3.GA")
                                .artifact("com.google.guava:guava:13.0.1")
                                .artifact("com.tool:Emailbot-common:1.0")
                                .resolveAsFiles())
                .addAsWebInfResource("ValidationMessages.properties")
                .addAsWebInfResource("jboss-ds.xml")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        return webArchive;
    }
}