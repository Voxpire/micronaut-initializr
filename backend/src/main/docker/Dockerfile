FROM oracle/graalvm-ce:19.2.0.1 as graalvm
COPY ../../.. /home/app/micronaut-initializr
WORKDIR /home/app/micronaut-initializr
RUN gu install native-image
RUN native-image --no-server -cp target/micronaut-initializr-*.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/micronaut-initializr .
ENTRYPOINT ["./micronaut-initializr"]
