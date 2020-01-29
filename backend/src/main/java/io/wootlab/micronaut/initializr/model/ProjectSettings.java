package io.wootlab.micronaut.initializr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSettings {

    @NotEmpty(message = "GroupId can't be empty or null")
    private String groupId;

    @NotEmpty(message = "ArtifactId can't be empty or null")
    private String artifactId;

    @NotNull(message = "BuildType can't be null")
    private BuildType buildType;

    @NotNull(message = "Version can't be null")
    private String version;

    @NotNull(message = "PackageName can't be null")
    private String packageName;

    private List<Feature> features;
}
