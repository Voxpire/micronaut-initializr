package io.wootlab.micronaut.initializr;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.referential.BuildType;
import io.wootlab.micronaut.initializr.api.representation.ProjectSettingsRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.AfterAll;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@MicronautTest
@Slf4j
public abstract class AbstractInitialzrTest {

    protected static final String TEST_GROUP_ID = "io.wootlab";
    protected static final String TEST_ARTIFACT_ID = "micronautinitializer";

    @AfterAll
    public static void eraseGeneratedProjects() {
        Arrays.asList(new File(".").listFiles()).stream()
                .filter(file -> file.getName().startsWith(TEST_ARTIFACT_ID))
                .forEach(file -> {
                    try {
                        FileUtils.deleteDirectory(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    protected ProjectSettingsRepresentation createProjectSettings() {
        ProjectSettingsRepresentation settings
                = new ProjectSettingsRepresentation(
                TEST_GROUP_ID,
                TEST_ARTIFACT_ID,
                BuildType.maven,
                "1.0",
                "com.test", Collections.EMPTY_LIST);
        return settings;
    }
}
