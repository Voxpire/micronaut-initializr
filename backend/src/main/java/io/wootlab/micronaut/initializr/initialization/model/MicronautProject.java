package io.wootlab.micronaut.initializr.initialization.model;

import io.wootlab.micronaut.initializr.api.representation.ProjectSettingsRepresentation;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@Data
@RequiredArgsConstructor
public class MicronautProject {

    @NonNull private final String uniqueName;
    @NonNull private final ProjectSettingsRepresentation settings;
    private String packageName;

    private InputStream inputStream;

}
