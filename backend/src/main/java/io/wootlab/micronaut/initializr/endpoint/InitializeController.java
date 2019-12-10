package io.wootlab.micronaut.initializr.endpoint;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.types.files.StreamedFile;
import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.Feature;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import io.wootlab.micronaut.initializr.service.Initializr;

import javax.inject.Inject;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class InitializeController {

    @Inject
    private Initializr initializr;

    @Post("/initialize")
    public StreamedFile initialize(@Valid ProjectSettings projectSettings) throws IOException, InitializrException {
        MicronautProject project = initializr.init(projectSettings);
       return new StreamedFile(project.getInputStream(), MediaType.TEXT_PLAIN_TYPE)
               .attach(project.getSettings().getGroupId() + ".zip");
    }

    @Get("/features")
    public Feature[] features() {
        return Feature.values();
    }
}