package io.wootlab.micronaut.initializr.initialization.patcher;

import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Slf4j
public class PatcherWrapper implements ProjectPatcher{

    @Inject
    private MavenProjectPatcher mavenPatcher;

    @Inject
    private PackageProjectPatcher packagePatcher;

    @Override
    public void patchProject(MicronautProject project) throws InitializrException {
        packagePatcher.patchProject(project);
        patchBuildFiles(project);
    }

    private void patchBuildFiles(MicronautProject project) throws InitializrException {
        switch (project.getSettings().getBuildType()){
            case maven:
                mavenPatcher.patchProject(project);
                break;
            case gradle:
                // TODO: gradle patcher
        }
    }
}
