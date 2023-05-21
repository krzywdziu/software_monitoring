# ZSTwP
## Design
Project is set as a multi-module maven project. Modules list:
* agent - apps/services monitoring based on provided configuration, sending alerts
* server - alert processing, notification controller, webui
* // common - common classes used across modules

## Local run (e.g. from IDE):
* connect to vpn-agh (if working with mysql_agh)
* configure your application.properties file(s)
* run docker-compose file from ./server/src/main/resources
* compile and run using intelliJ (server OR server + agent (for kafka streaming))
* alternatively
  * from linux terminal: `./mvnw clean install` OR windows cmd: `mvnw.cmd clean install` 
  + run (server) with `java -jar ./server/target/server-0.0.1-SNAPSHOT.jar` (Java 17)

## api documentation
* http://localhost:8080/swagger-ui.html
