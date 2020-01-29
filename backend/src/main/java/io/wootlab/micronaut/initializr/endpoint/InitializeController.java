package io.wootlab.micronaut.initializr.endpoint;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.types.files.StreamedFile;
import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.Feature;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import io.wootlab.micronaut.initializr.representation.FeatureRepresentation;
import io.wootlab.micronaut.initializr.service.Initializr;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InitializeController {

    @Inject
    private Initializr initializr;

    @Post("/initialize")
    public StreamedFile initialize(@Valid @Body ProjectSettings projectSettings) throws IOException, InitializrException {
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