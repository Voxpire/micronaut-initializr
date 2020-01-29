package io.wootlab.micronaut.initializr.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureRepresentation {
    private String name;
    private String description;
}
