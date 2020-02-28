package io.wootlab.micronaut.initializr.initialization;

import io.wootlab.micronaut.initializr.api.representation.ProjectSettingsRepresentation;
import io.wootlab.micronaut.initializr.initialization.compression.ZipCompressor;
import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;
import io.wootlab.micronaut.initializr.initialization.patcher.PatcherWrapper;
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
    private ZipCompressor compressor;

    public MicronautProject init(ProjectSettingsRepresentation projectSettings) throws IOException, InitializrException {

        MicronautProject project = cliWrapper.generateProject(projectSettings);

        patcher.patchProject(project);

        project.setInputStream(compressor.compressProject(project));
        FileUtils.deleteDirectory(project.getUniqueName());

        return project;
    }
}
