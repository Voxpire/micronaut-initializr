package io.wootlab.micronaut.initializr.service;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.AbstractInitialzrTest;
import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@MicronautTest
class MicronautProjectPatcherTest extends AbstractInitialzrTest {

    @Inject
    private MicronautProjectPatcher patcher;

    @Inject
    private MicronautCliWrapper wrapper;

    @Test
    void patchBuildFile() throws InitializrException, IOException {
        MicronautProject project = wrapper.generateProject(createProjectSettings());
        patcher.patchProject(project);

        File f = new File(
                project.getUniqueName() + File.separator + "src"
                        + File.separator + "main" + File.separator + "java" + File.separator
                        + project.getPackageName().replaceAll("\\.", File.separator) + File.separator + "Application.java");
        String content = Files.readString(f.toPath());
        Assert.assertTrue(content.contains("package " + project.getPackageName()));
    }


}