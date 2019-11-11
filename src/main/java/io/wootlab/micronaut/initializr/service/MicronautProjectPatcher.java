package io.wootlab.micronaut.initializr.service;

import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
class MicronautProjectPatcher {

    private static final String PROJECT_ARTIFACT_ID_MVN = "/project/artifactId/text()";
    private static final String PROJECT_GROUP_ID_MVN = "/project/groupId/text()";
    private static final String MAIN_CLASS_ID_MVN = "/project/properties/exec.mainClass/text()";


    void patchProject(MicronautProject project) throws InitializrException {
        patchPackage(project);
        patchBuildFile(project);
    }

    private void patchPackage(MicronautProject project) throws InitializrException {
        project.setPackageName(project.getSettings().getGroupId() + "." + formatArtifactToPackageName(project.getSettings().getArtifactId()));
        AtomicReference pathToParent = new AtomicReference(project.getUniqueName() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        File oldPackage = new File(pathToParent + File.separator + project.getUniqueName());

        for (String member : project.getPackageName().split("\\.")) {
            File current = new File(pathToParent + File.separator + member);

            if (!current.exists()) {
                try {
                    Files.createDirectory(current.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            pathToParent.set(pathToParent.get() + File.separator + member);
        }

        for(File f : oldPackage.listFiles()){
            try {
                String content = Files.readString(f.toPath());
                content = content.replaceAll("package " + project.getUniqueName(), "package " + project.getPackageName());
                Files.write(Path.of((String)pathToParent.get() + File.separator + f.getName()), content.getBytes( StandardCharsets.UTF_8));

            } catch (IOException e) {
                log.error("Error while patching pom.xml", e);
                throw new InitializrException(e.getMessage(), e.getClass().getSimpleName(), e.getCause());
            }
        }

        try {
            FileUtils.deleteDirectory(oldPackage);
        } catch (IOException e) {
            log.error("Error while patching pom.xml", e);
            throw new InitializrException(e.getMessage(), e.getClass().getSimpleName(), e.getCause());
        }
    }

    private String formatArtifactToPackageName(String artifactId) {
        return artifactId.replaceAll("-", "");
    }

    private void patchBuildFile(MicronautProject project) throws InitializrException {
        switch (project.getSettings().getBuildType()) {
            case maven:
                patchMavenBuildFile(project);
                break;
            case gradle:
                break;
            default:
        }
    }

    private void patchMavenBuildFile(MicronautProject project) throws InitializrException {
        File pom = new File(project.getUniqueName() + File.separator + "pom.xml");

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(pom);
            XPath xpath = XPathFactory.newInstance().newXPath();

            replaceField(doc, xpath, PROJECT_ARTIFACT_ID_MVN, project.getSettings().getArtifactId());
            replaceField(doc, xpath, PROJECT_GROUP_ID_MVN, project.getSettings().getGroupId());
            replaceField(doc, xpath, MAIN_CLASS_ID_MVN, project.getPackageName() + ".Application");

            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(new DOMSource(doc), new StreamResult(pom));


        } catch (SAXException | ParserConfigurationException | IOException | XPathExpressionException | TransformerException e) {
            log.error("Error while patching pom.xml", e);
            throw new InitializrException(e.getMessage(), e.getClass().getSimpleName(), e.getCause());
        }
    }

    private void replaceField(Document doc, XPath xpath, String xpathFormula, String value) throws XPathExpressionException {
        NodeList nodes = (NodeList) xpath.evaluate(xpathFormula, doc, XPathConstants.NODESET);
        nodes.item(0).setTextContent(value);
    }
}
