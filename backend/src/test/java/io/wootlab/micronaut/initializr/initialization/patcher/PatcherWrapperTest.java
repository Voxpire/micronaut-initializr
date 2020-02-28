package io.wootlab.micronaut.initializr.initialization.patcher;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.AbstractInitialzrTest;
import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;
import io.wootlab.micronaut.initializr.initialization.MicronautCliWrapper;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@MicronautTest
class PatcherWrapperTest extends AbstractInitialzrTest {

    @Inject
    private PatcherWrapper patcher;

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