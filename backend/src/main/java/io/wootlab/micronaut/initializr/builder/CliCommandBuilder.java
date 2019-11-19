package io.wootlab.micronaut.initializr.builder;

import io.micronaut.core.util.StringUtils;
import io.wootlab.micronaut.initializr.model.BuildType;
import io.wootlab.micronaut.initializr.model.CliCommand;
import io.wootlab.micronaut.initializr.model.Feature;

import javax.validation.constraints.NotNull;

public class CliCommandBuilder {

    private CliCommand command;
    public final static String CREATE_APP_COMMAND = "create-app";

    public static CliCommandBuilder init(@NotNull String artifactId){
        if(StringUtils.isEmpty(artifactId)) {
            throw new IllegalArgumentException("artifactId can't be null, please provide one.");
        }
        return new CliCommandBuilder(artifactId);
    }

    private CliCommandBuilder(String artifactId){
        this.command = new CliCommand();
        this.command.add(CREATE_APP_COMMAND);
        this.command.add(artifactId);
    }

    public CliCommandBuilder withBuildType(BuildType gradle) {
        this.command.add("-b");
        this.command.add(gradle.name());
        return this;
    }

    public CliCommandBuilder withFeature(Feature feature) {
        this.command.add("-f");
        this.command.add(feature.getValue());
        return this;
    }

    public CliCommand build(){
        return this.command;
    }
}
