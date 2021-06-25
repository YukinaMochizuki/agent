# agent

Use HTTP protocol to trigger specific commands or simulate keyboard operations, and support for compiling to native executables using the Spring Native and GraalVM native-image compiler.

My desired vision can be summed up in a sentence:

> The “Last Mile” in personal computer auxiliary control system.

Although it is still far away, but I am working hard.

## General Information
- Project status: **Active**, medium priority

## Technologies Used
### Development environment
- GraalVM 21.0.0.2 Java 11 based
- Build System: Apache Maven 3.8.1
- Software Development Kit Manager: SDKMAN!

### Dependencies
- Spring Boot 2.5.0
  - Spring Boot Starter Web
  - Spring Boot Starter Validation: For security reasons, all parameters from the request need to be strictly verified
- Spring Native 0.10.0-SNAPSHOT
- [Project Lombok](https://projectlombok.org/)

## Build
### Native Executable
#### Requirements
- GraalVM 21+ Java 11 based
- GraalVM Native Image
- High CPU and memory usage: Please make sure you have enough patience and least 6 GB of memory is available

If you want to build JAR, just use `./mvnw package`.

```Shell
git clone https://github.com/YukinaMochizuki/agent.git
cd agent
./mvnw -Pnative-image package
```

Then native executable will be created in `target` folder and named `tw.yukina.agent.application`.

### Debian Package
#### Requirements
- debhelper
- dh-systemd

Make sure build native executable is been completed successfully.

```Shell
cd build-deb/shuvi-agent/
vim debian/shuvi-agent.service //change User=mochizuki to your logined user name
dpkg-buildpackage -us -uc //build deb
```

