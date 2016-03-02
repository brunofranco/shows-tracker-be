# shows-tracker-be

backend for shows-tracker build on dropwizard framework

    mvn install

    java -jar target/showtracker-1.0-SNAPSHOT.jar server show-tracker.yml

-- 
## Endpoints

http://localhost:9000/api/shows -> lists your shows

http://localhost:9000/api/shows/tt1856010 -> adds/shows the show based on its imdb_id (tt1856010 - house of cards)

--

##Heroku

* Uses the environment variable "DATABASE_URL" to connect to the database overriding the one from the yml
* Overrides the application port in the yml to use the variable $PORT from heroku
* Procfile
    * Runs the migrations
    * Overrides the default application port
    * Runs the server