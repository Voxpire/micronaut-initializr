package io.wootlab.micronaut.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ProjectSettings {

    @NotEmpty(message = "GroupId can't be empty or null")
    private final String groupId;

    @NotEmpty(message = "ArtifactId can't be empty or null")
    private final String artifactId;

    @NotNull(message = "BuildType can't be null")
    private final BuildType buildType;

    @NotNull(message = "Version can't be null")
    private final String version;

    @NotNull(message = "PAckageName can't be null")
    private final String packageName;
}
