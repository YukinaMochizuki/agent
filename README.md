# agent

Use HTTP protocol to trigger specific commands or simulate keyboard operations, and support for compiling to native executables using the Spring Native and GraalVM native-image compiler.

My desired vision can be summed up in a sentence:

> The “Last Mile” in personal computer auxiliary control system.

Although it's still far away, but I am working hard.

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

### Other dependencies with your runtime environment
- i3wm or i3-grap - Use `i3-msg` to control window manager
- xdotool - Simulate keyboard input and mouse activity

### Why native?
Speed and experimental spirit. It took 3 seconds to start the packaged jar, but native executable only took less than 0.2. Obviously there's a very big gap between them.

![](https://i.imgur.com/orh4j5t.png)

![](https://i.imgur.com/Li3fqh2.png)

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

Make sure build native executable is been completed and successfully.

```Shell
mkdir build-deb/shuvi-agent/bin
mv target/tw.yukina.agent.application build-deb/shuvi-agent/bin/shuvi-agent
cd build-deb/shuvi-agent/
vim debian/shuvi-agent.service //change User=mochizuki to your logined user name
dpkg-buildpackage -us -uc //build deb
```
## Install

After building the deb file.

```Shell
cd ..
sudo dpkg -i shuvi-agent_0.0.2-1_amd64.deb
```

[![asciicast](https://asciinema.org/a/E4gkPLOrJKZaTl32t9SThcYtX.svg)](https://asciinema.org/a/E4gkPLOrJKZaTl32t9SThcYtX)

## Use case and demo
### About Joplin
Although I'm a heavy user of Notion, but some sensitive information still shouldn't be stored on it (like any password or personal info that's too private). Because even though Notion says they has the highest level of privacy policy, but the truth is Notion still do not support e2e encryption. It shouldn't be completely trusted.

So usually I store these data on open source Note-taking solutions that support e2e encryption, like [Joplin](https://joplinapp.org). Then how to seamlessly connect Notion and Joplin has become a thorny issue, this section are my attempts at it.

### Switch window to Joplin then opens the specific note




