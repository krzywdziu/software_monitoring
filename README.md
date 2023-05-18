# ZSTwP
## Design
Project is set as a multi-module maven project. Modules list:
* client - apps/services monitoring based on provided configuration, sending alerts
* server - alert processing, notification controller, webui
* common - common classes used across modules

## Local run (e.g. from IDE):
* connect to vpn-agh 
* compile and run using intelliJ
* alternatively
  * from linux terminal: `./mvnw clean install` OR windows cmd: `mvnw.cmd clean install` 
  + run with `java -jar ./server/target/server-0.0.1-SNAPSHOT.jar` (Java 17)

## api documentation
* http://localhost:8080/swagger-ui.html