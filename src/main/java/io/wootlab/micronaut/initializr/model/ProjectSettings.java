package io.wootlab.micronaut.initializr.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProjectSettings {

    @NotEmpty(message = "GroupId can't be empty or null")
    private String groupId;

    @NotEmpty(message = "ArtifactId can't be empty or null")
    private String artifactId;

    @NotNull(message = "BuildType can't be null")
    private BuildType buildType;
}
