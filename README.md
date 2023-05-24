# ZSTwP
## Design
Project is a collection of independent services that together form a whole system. Services so far:
* agent - apps/services monitoring based on provided configuration, sending alerts
* server - alert processing, notification controller, webui
* ~~// common - common classes used across modules~~
* webui - user-friendly interface allowing for painless alerts & users management

## Local run (e.g. from IDE):
* connect to vpn-agh (if working with mysql_agh)
* configure your application.properties file(s)
* run docker-compose file from ./server/src/main/resources
* compile and run using intelliJ (server OR server + agent (for kafka streaming))
* alternatively
  * from linux terminal: `./mvnw clean install` OR windows cmd: `mvnw.cmd clean install` 
  + run (server) with `java -jar ./server/target/server-0.0.1-SNAPSHOT.jar` (Java 17)
* run frontend typing `ng serve` from /webui/angular-app

## Server's api documentation
* http://localhost:8080/swagger-ui.html


### run using docker compose

```bash
docker compose up --build -d
```
