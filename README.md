# Cooldowns
Standalone library used to apply cooldowns to UUIDs. This library is intended to allow for creating cooldowns on game environments a lot more practical and easier by not having to do math or create a Map for every new cooldown.

# Download
To setup Cooldown usage with maven, put the following in your pom.xml

```xml
<dependencies>
    <!-- Depend on & shade in Cooldowns -->
    <dependency>
        <groupId>cc.revity</groupId>
        <artifactId>cooldowns</artifactId>
        <version>1.0.0</version>
        <scope>compile</scope>
    </dependency>
</dependencies>

```

# Compilation
Compilation requires the following to be fulfilled:
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html "Java 8 Link")
* [Maven 3](http://maven.apache.org/download.html "Maven 3 Link")

# Updates
This library is provided "as is", which means no updates or new features are guaranteed. We will do our best to keep updating and pushing new updates, and you are more than welcome to contribute your time as well and make pull requests for bug fixes.

Once these tasks have been taken care of, compilation via `mvn clean install` will result in `target/cooldowns-1.0.0.jar` being created.

# License
This software is available under the following licenses:
* GNU General Public License (GPL) version 3

