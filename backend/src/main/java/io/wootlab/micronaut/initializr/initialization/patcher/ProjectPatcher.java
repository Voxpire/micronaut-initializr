package io.wootlab.micronaut.initializr.initialization.patcher;

import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;

interface ProjectPatcher {
    void patchProject(MicronautProject project) throws InitializrException;
}
