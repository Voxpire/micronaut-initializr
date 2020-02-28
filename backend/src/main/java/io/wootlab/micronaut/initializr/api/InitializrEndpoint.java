package io.wootlab.micronaut.initializr.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.types.files.StreamedFile;
import io.wootlab.micronaut.initializr.initialization.Initializr;
import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import io.wootlab.micronaut.initializr.referential.Feature;
import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;
import io.wootlab.micronaut.initializr.api.representation.ProjectSettingsRepresentation;
import io.wootlab.micronaut.initializr.api.representation.FeatureRepresentation;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InitializrEndpoint {

    @Inject
    private Initializr initializr;

    @Inject
    private SettingsValidator validator;

    @Post("/initialize")
    public StreamedFile initialize(@Valid @Body ProjectSettingsRepresentation projectSettings) throws IOException, InitializrException {
        validator.validate(projectSettings);
        MicronautProject project = initializr.init(projectSettings);
        return new StreamedFile(project.getInputStream(), MediaType.TEXT_PLAIN_TYPE)
                .attach(project.getSettings().getGroupId() + ".zip");
    }

    @Get("/features")
    public List<FeatureRepresentation> features() {
        return Arrays.asList(Feature.values())
                .stream()
                .map(Feature::toFeatureRepresentation)
                .collect(Collectors.toList());
    }
}