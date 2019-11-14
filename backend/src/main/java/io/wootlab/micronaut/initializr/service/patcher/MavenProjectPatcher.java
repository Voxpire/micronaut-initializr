package io.wootlab.micronaut.initializr.service.patcher;

import io.wootlab.micronaut.initializr.exception.InitializrException;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import lombok.extern.slf4j.Slf4j;
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

@Singleton
@Slf4j
class MavenProjectPatcher implements ProjectPatcher {

    private static final String PROJECT_ARTIFACT_ID_MVN = "/project/artifactId/text()";
    private static final String PROJECT_GROUP_ID_MVN = "/project/groupId/text()";
    private static final String MAIN_CLASS_ID_MVN = "/project/properties/exec.mainClass/text()";

    @Override
    public void patchProject(MicronautProject project) throws InitializrException{
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
