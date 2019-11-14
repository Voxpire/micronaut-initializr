package io.wootlab.micronaut.initializr.service.patcher;

import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.BuildType;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicReference;

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
