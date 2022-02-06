# MapStruct Spring Lazy

MapStruct Spring Lazy adds additional `@Lazy` Annotation to every Mapper, which is referenced with [uses](https://mapstruct.org/documentation/stable/reference/html/#invoking-other-mappers). Adding `@Lazy` can break a circular references, which is no longer allowed in [Spring Boot 2.6](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes#circular-references-prohibited-by-default).

## How to install

Add Jar to your dependencies

### Maven

```
...
<properties>
    <org.mapstruct.version>1.4.2.Final</org.mapstruct.version>
</properties>
...
<dependencies>
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${org.mapstruct.version}</version>
    </dependency>
</dependencies>
...
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source> <!-- depending on your project -->
                <target>1.8</target> <!-- depending on your project -->
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>${org.mapstruct.version}</version>
                    </path>
                    <path>
                        <groupId>com.github.anweber</groupId>
                        <artifactId>mapstruct-springlazy</artifactId>
                        <version>1.0.0</version>
                    </path>
                    <!-- other annotation processors -->
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Gradle
```
dependencies {
    ...
    implementation 'org.mapstruct:mapstruct:1.4.2.Final'
 
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.4.2.Final'
    annotationProcessor 'com.github.anweber:mapstruct-springlazy:1.0.0'
}
```


## How to use

Use `springlazy` as [componentModel](https://mapstruct.org/documentation/stable/reference/html/#configuration-options)

* springlazy: the generated mapper is a singleton-scoped Spring bean and can be retrieved via @Autowired and @Lazy


```java
@Mapper( componentModel = "springlazy",  uses = { FooMapper.class })
public interface BarMapper {
    Bar toBo(BarDto dto);
    BarDto toDto(Bar obj) ;
}
```


## Building from source

MapStruct-springlazy uses gradle for the build. Java 11 is required to build from source.

```
gradlew build
```

## License

[MIT License](LICENSE)