package io.wootlab.micronaut.initializr;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.model.BuildType;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@MicronautTest
@Slf4j
public abstract class AbstractInitialzrTest {

    static final String TEST_GROUP_ID = "io.wootlab";
    static final String TEST_ARTIFACT_ID = "micronautinitializer";

    @AfterAll
    public static void eraseGeneratedProjects(){
        Arrays.asList(new File(".").listFiles()).stream()
                .filter(file->file.getName().startsWith(TEST_ARTIFACT_ID))
                .forEach(file -> {
                    try {
                        FileUtils.deleteDirectory(file);
                    } catch (IOException e) {
                        log.error("Error deleting project " + file.getName(), e);
                    }
                });
    }

    protected ProjectSettings createProjectSettings(){
        ProjectSettings settings = new ProjectSettings();
        settings.setArtifactId(TEST_ARTIFACT_ID);
        settings.setGroupId(TEST_GROUP_ID);
        settings.setBuildType(BuildType.maven);
        return settings;
    }
}
