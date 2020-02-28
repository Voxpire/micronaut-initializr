package io.wootlab.micronaut.initializr.builder;

import io.micronaut.test.annotation.MicronautTest;
import io.wootlab.micronaut.initializr.initialization.CliCommandBuilder;
import io.wootlab.micronaut.initializr.referential.BuildType;
import io.wootlab.micronaut.initializr.referential.Feature;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class CliCommandBuilderTest {

    @Test
    public void initWithNullArtifact(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> CliCommandBuilder.init(null));
    }

    @Test
    public void initWithEmptyArtifact(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> CliCommandBuilder.init(""));
    }

    @Test
    public void emptyBuild(){

        CliCommandBuilder.CliCommand command = CliCommandBuilder.init("test").build();

        Assert.assertEquals(2, command.size());
        Assert.assertEquals(CliCommandBuilder.CREATE_APP_COMMAND, command.get(0));
        Assert.assertEquals("test",command.get(1));
    }

    @Test
    public void buildType(){
        CliCommandBuilder.CliCommand command = CliCommandBuilder.init("test")
                .withBuildType(BuildType.gradle).build();

        Assert.assertEquals(4, command.size());
        Assert.assertEquals("-b", command.get(2));
        Assert.assertEquals("gradle", command.get(3));
    }

    @Test
    public void feature(){
        CliCommandBuilder.CliCommand command = CliCommandBuilder.init("test")
                .withFeature(Feature.kafka).build();
        Assert.assertEquals(4, command.size());
        Assert.assertEquals("-f", command.get(2));
        Assert.assertEquals("kafka", command.get(3));

    }
}
