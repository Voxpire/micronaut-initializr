package io.wootlab.micronaut.initializr.service;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.AbstractInitialzrTest;
import io.wootlab.micronaut.initializr.model.BuildType;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class SettingsValidatorTest extends AbstractInitialzrTest {

    @Inject
    private SettingsValidator validator;

    @Test
    void testBean() {
        assertNotNull(validator);
    }

    @Test
    void validate_nullObject_shouldThrowException() {
        Assertions.assertThrows(ValidationException.class, () -> validator.validate(null));
    }

    @Test
    void validate_emptyObject_shouldThrowException() {
        Assertions.assertThrows(ValidationException.class, () -> validator.validate(new ProjectSettings(null, null, null, null, null)));
    }

    @Test
    void validate_nullGroupId_shouldThrowException() {
        ProjectSettings settings = new ProjectSettings(
                null,
                TEST_ARTIFACT_ID,
                BuildType.maven,
                "",
                "");

        Exception thrown = Assertions.assertThrows(ValidationException.class, () -> validator.validate(settings));

        assertTrue(thrown.getMessage().contains("GroupId can't be"));
    }

    @Test
    void validate_emptyGroupId_shouldThrowException() {
        ProjectSettings settings = new ProjectSettings(
                "",
                TEST_ARTIFACT_ID,
                BuildType.maven,
                "","");

        ValidationException thrown = Assertions.assertThrows(ValidationException.class, () -> validator.validate(settings));

        assertTrue(thrown.getMessage().contains("GroupId can't be"));
    }


    @Test
    void validate_nullArtifactId_shouldThrowException() {
        ProjectSettings settings = new ProjectSettings(
                TEST_GROUP_ID,
                null,
                BuildType.maven,
                "","");

        Exception thrown = Assertions.assertThrows(ValidationException.class, () -> validator.validate(settings));

        assertTrue(thrown.getMessage().contains("ArtifactId can't be"));
    }

    @Test
    void validate_emptyArtifactId_shouldThrowException() {
        ProjectSettings settings = new ProjectSettings(
                TEST_GROUP_ID,
                "",
                BuildType.maven,
                "","");

        Exception thrown = Assertions.assertThrows(ValidationException.class, () -> validator.validate(settings));

        assertTrue(thrown.getMessage().contains("ArtifactId can't be"));
    }

    @Test
    void validate_nullBuildType_shouldThrowException() {
        ProjectSettings settings = new ProjectSettings(
                TEST_GROUP_ID,
                TEST_ARTIFACT_ID,
               null,
                "","");

        Exception thrown = Assertions.assertThrows(ValidationException.class, () -> validator.validate(settings));

        assertTrue(thrown.getMessage().contains("BuildType can't be"));
    }
}