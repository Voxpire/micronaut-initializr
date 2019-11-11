package io.wootlab.micronaut.initializr.service;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.AbstractInitialzrTest;
import io.wootlab.micronaut.initializr.model.CliCommand;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;

@MicronautTest
class MicronautCliWrapperInitialzrTest extends AbstractInitialzrTest {

    @Inject
    private MicronautCliWrapper cliWrapper;

    @Test
    void testSingleton() {
        Assert.assertNotNull(cliWrapper);
    }

    @Test
    void buildCommandCli(){
        CliCommand command = cliWrapper.buildCliCommand(createProjectSettings());
        Assert.assertEquals(4, command.size());
    }

    @Test
    void generateProject(){
        MicronautProject project = cliWrapper.generateProject(createProjectSettings());
        Assert.assertTrue(new File(project.getUniqueName()).exists());
    }
}
