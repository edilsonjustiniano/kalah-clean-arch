# Kalah game


## Game's origin
Kalah, also called Kalaha or Mancala, is a game in the mancala family imported in the United States by William Julius Champion, Jr. in 1940.
This game is sometimes also called "Kalahari", possibly by false etymology from the Kalahari desert in Namibia.


## Kalah's rule
Each of the two players has **siz pits** in front of him/her. To the right of the six pits, each player has a larger pit, his Kalah or house.

At the start of the game, six stones are put in each pit.

The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in each of the following pits,
including his own Kalah. No stones are put in the opponent's' Kalah. If the players last stone lands in his own Kalah, he gets another turn.
This can be repeated any number of times before it's the other player's turn.

When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the other players' pit)
and puts them in his own Kalah.

The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her
pits keeps them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.

## Solution

To implement the game according its rule, it was created a Spring Boot RESTfull API and a terminal APP as well. using the following the following technologies:

- Java 14
- Spring DI
- Spring Boot
- RESTfull API
- Gradle
- Lombok
- Mockito
- Junit 5

In this repository you will going to see a program highly scalable, testable, readable and maintainable. For those reason
the project is easily deployed since it is using some of the best approach and technologies in the market.

I used as principle the `Clean Architecture` and `SOLID`. I also used the `KISS` (Keep it simple, stupid) principle as well.

The project structure and the responsibilities are stated below:

- `adapter` is the module that contains the classes responsible to perform the adapter (link) between the external world (external solutions). At this moment I left only the controller adapter on this module. I haven't include the repository because I was trying to validate how much worth keep the adapters to the repository or have their owns module called `repository` as I did.


- `application` is the module responsible to decide which kind of application will be run, either a Spring boot with a REST API or a terminal app.

- `domain` is the module that contains the entities and some of the business logic that only makes sense to the entities.


- `usecase` is the module that contains the interfaces/classes responsible to make all use cases works fine. Create a game, Search for a game, Delete it or Play the game by moving a pit.
It requires the modules: repository and domain since it is the responsible to link the requests that come from the `adapter.controller` and perform the business logic implemented here, then save the game to the database (regardless of its type).
`api` is the package that contains the REST API definition for the game domain.
It is split in the following sub-packages: `controller`, `exception`, `model`, `service`.


- `repository` is the module that contains the implementation of database. At this moment, I have implemented only a in-memory-db (a simple `Map<String, Game>`) in order to keep it as simple as possible.

## Design patterns

- Strategy (not completely for Database instantiation)
- Tell don't ask
- Singleton for bean instantiation
- Builder


## Prerequisites

It is necessary the followings to build the program:
- Latest gradle version
- JDK 14+

To run the program, it is required:
- JRE 14+


## How to run

To build the application execute the following command on terminal:

```
gradle clean build
```


## How to test

You can use the following rest calls to validate the application:

> Create a game

```
curl --header "Content-Type: application/json" \ 
     --request POST \ 
     localhost:8080/games
```

> Retrieve a game

```
curl --header "Content-Type: application/json" \ 
     --request GET \ 
     localhost:8080/games/<gameId>
```

> Make a movement

```
curl --header "Content-Type: application/json" \ 
     --request PUT \ 
     localhost:8080/games/<gameId>/pits/<pitId>
```

## Future improvements

Improve the `repository` module by adding to it the capacity to support different database implementation such as mongodb.