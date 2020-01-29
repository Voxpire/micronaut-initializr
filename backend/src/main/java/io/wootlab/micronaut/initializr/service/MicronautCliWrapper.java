package io.wootlab.micronaut.initializr.service;

import io.micronaut.cli.MicronautCli;
import io.wootlab.micronaut.initializr.builder.CliCommandBuilder;
import io.wootlab.micronaut.initializr.model.CliCommand;
import io.wootlab.micronaut.initializr.model.MicronautProject;
import io.wootlab.micronaut.initializr.model.ProjectSettings;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.util.Date;

@Singleton
@Slf4j
public class MicronautCliWrapper {

    private final MicronautCli cli = new MicronautCli();

    public MicronautProject generateProject(ProjectSettings settings){
        var uniqueProjectName = createUniqueProjectName(settings.getArtifactId());
        var micronautCliArgs = buildCliCommand(settings).toArray(new String[0]);

        micronautCliArgs[1] = uniqueProjectName;
        cli.execute(micronautCliArgs);

        return new MicronautProject(uniqueProjectName, settings);
    }

    CliCommand buildCliCommand(ProjectSettings settings){
        var builder = CliCommandBuilder.init(settings.getArtifactId())
                .withBuildType(settings.getBuildType());
        settings.getFeatures().stream().forEach(feature -> builder.withFeature(feature));
        return builder.build();
    }

    private String createUniqueProjectName(String groupId) {
        var dateFormatter = new SimpleDateFormat("yyyyMMddhhmmss");
        var uniqueSuffix = dateFormatter.format(new Date());
        return groupId.replaceAll("-", "") + uniqueSuffix;
    }

}
