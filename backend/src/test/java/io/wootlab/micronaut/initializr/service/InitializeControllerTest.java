package io.wootlab.micronaut.initializr.service;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.model.Feature;
import io.wootlab.micronaut.initializr.representation.FeatureRepresentation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@MicronautTest
public class InitializeControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void testFeatures() throws Exception {
        var result = client.toBlocking().retrieve(HttpRequest.GET("/features"), Argument.listOf(FeatureRepresentation.class));
        Assertions.assertEquals(Feature.values().length, result.size());
    }
}
