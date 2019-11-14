package io.wootlab.micronaut.initializr.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@Data
@RequiredArgsConstructor
public class MicronautProject {

    @NonNull private final String uniqueName;
    @NonNull private final ProjectSettings settings;
    private String packageName;

    private InputStream inputStream;

}
