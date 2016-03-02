
web: java $JAVA_OPTS -jar target/showtracker-1.0-SNAPSHOT.jar db migrate show-tracker.yml && java $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -jar target/showtracker-1.0-SNAPSHOT.jar server show-tracker.yml
