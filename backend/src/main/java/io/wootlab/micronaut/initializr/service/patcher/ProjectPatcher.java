package io.wootlab.micronaut.initializr.service.patcher;

import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.MicronautProject;

interface ProjectPatcher {
    void patchProject(MicronautProject project) throws InitializrException;
}
