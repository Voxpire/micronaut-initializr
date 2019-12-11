package io.wootlab.micronaut.initializr.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeatureRepresentation {
    private final String name;
    private final String description;
}
