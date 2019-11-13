package io.wootlab.micronaut.initializr.service;

import io.wootlab.micronaut.initializr.model.MicronautProject;

import javax.inject.Singleton;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Singleton
class CompressionService {

    enum CompressionType{
        zip
    }

    InputStream compressProject(MicronautProject project) throws IOException {
        return compressProject(project,CompressionType.zip);
    }

    private InputStream compressProject(MicronautProject project, CompressionType compressionType) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream( baos);

        for(File file : new File(project.getUniqueName()).listFiles()){
            addFileToZip(zipOutputStream, ".", file.toPath());
        }

        zipOutputStream.close();

        var result = new ByteArrayInputStream( baos.toByteArray() );
        baos.close();
        return result;
    }

    private void addFileToZip(ZipOutputStream outputStream, String parent, Path path) {
        if (path.toFile().isDirectory()) {
            for (File child : path.toFile().listFiles()) {
                addFileToZip(outputStream, parent + File.separator + path.getFileName(), child.toPath());
            }
        } else {
            ZipEntry zipEntry = new ZipEntry(parent + File.separator + path.toFile().getName());

            zipEntry.setCreationTime(FileTime.fromMillis(path.toFile().lastModified()));
            zipEntry.setComment("Created by Micronaut initializr");

            try {
                outputStream.putNextEntry(zipEntry);
                Files.copy(path, outputStream);
                outputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
