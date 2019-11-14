package io.wootlab.micronaut.initializr.service;

import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import io.wootlab.micronaut.initializr.service.patcher.PatcherWrapper;
import org.codehaus.plexus.util.FileUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class Initializr {

    @Inject
    private MicronautCliWrapper cliWrapper;

    @Inject
    private PatcherWrapper patcher;

    @Inject
    private CompressionService compressor;

    @Inject
    private SettingsValidator validator;

    public MicronautProject init(ProjectSettings projectSettings) throws IOException, InitializrException {

        validator.validate(projectSettings);

        MicronautProject project = cliWrapper.generateProject(projectSettings);

        patcher.patchProject(project);

        project.setInputStream(compressor.compressProject(project));
        FileUtils.deleteDirectory(project.getUniqueName());

        return project;
    }
}
