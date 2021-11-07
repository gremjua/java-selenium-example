FROM maven:3.8-jdk-11

RUN mkdir /workdir
COPY pom.xml /workdir
COPY ./src /workdir/src
WORKDIR /workdir

RUN mvn clean compile

CMD ["mvn", "clean", "test"]
