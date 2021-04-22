This is an API for Url shortener service like tiny url. 

# How to use 
+ With Docker and docker-compose: 

```sh
$ git clone https://github.com/Kamil-Galaz/shorturl.git
$ cd UrlShortener-API 
$ docker-compose up 
```

    - Open localhost:8080/swagger-ui.html to see endpoints. 

- Without Docker: 
```sh
$ git clone https://github.com/Kamil-Galaz/shorturl.git
```
    - Make sure you have access to local or any MySQL server.
    - Open project in your favorite editor and change application.properties file to point to your MySQL database
    - Build Spring project 
    - Open localhost:8080/swagger-ui.html to see endpoints.

