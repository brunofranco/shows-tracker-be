# shows-tracker-be

backend for shows-tracker build on dropwizard framework

mvn install

java -jar target/showtracker-1.0-SNAPSHOT.jar server show-tracker.yml

-- 

http://localhost:9000/api/shows -> lists your shows

http://localhost:9000/api/shows/tt1856010 -> adds/shows the show based on its imdb_id (tt1856010 - house of cards)