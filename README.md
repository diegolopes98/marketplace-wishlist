# Marketplace Wishlist

## Description

Build a customer's wishlist crud where you can add a new wish, delete a wish, get all wishes and get a specific wish from it. 
Do not worry about authentication, products or customers data management and each customer must have a maximum of 20 wishes.

## Technologies

- ```java: 17.0.2``` as programming language
- ```spring boot: 2.7.1``` as framework
- ```cassandra: 4.0.4``` as database
- ```cucumber: 7.3.3``` as testing framework
- ```swagger: 3.0.0``` as api documentation

## Develop Environment

You can run ```mvn spring-boot:run``` to start up the application at port ```8080```, in order to the application starts
well you must have a cassandra database running at port ```9042``` but if you don't have use ```docker-compose up``` after running the shell
script ```.cassandra/setup_docker.sh``` to start it. Once you see the bellow message at the terminal the container is ready:

```
wishlist-cassandra-load-schema exited with code 0
```

The tests can be run with ```mvn clean test``` and also needs the database running at the specified port.

## Final Considerations

* Apply some validation pattern to domain layer in order to move max limit validation to the domain. (Note: code highlighted with TODO comment)
* I didn't know about cassandra at the time I started the test so perhaps my modeling isn't the best to the situation.
* Do a better assertion of the api responses in the e2e tests.
