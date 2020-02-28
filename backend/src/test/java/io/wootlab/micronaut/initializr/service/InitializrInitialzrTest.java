package io.wootlab.micronaut.initializr.service;

import io.wootlab.micronaut.initializr.AbstractInitialzrTest;
import io.wootlab.micronaut.initializr.initialization.Initializr;
import io.wootlab.micronaut.initializr.initialization.model.InitializrException;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.io.IOException;

class InitializrInitialzrTest extends AbstractInitialzrTest {

    @Inject
    private Initializr initializr;

    @Test
    void testSingleton(){
        Assert.assertNotNull(initializr);
    }

    @Test
    void init() throws IOException, InitializrException {
        initializr.init(createProjectSettings());
    }

}