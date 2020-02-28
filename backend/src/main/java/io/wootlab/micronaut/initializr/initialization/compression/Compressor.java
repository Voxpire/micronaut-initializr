package io.wootlab.micronaut.initializr.initialization.compression;

import io.wootlab.micronaut.initializr.initialization.model.MicronautProject;

import java.io.IOException;
import java.io.InputStream;

public interface Compressor {

    enum CompressionType{
        zip
    }

    InputStream compressProject(MicronautProject project)  throws IOException;
}
