# Flashcard Webapp using SpringBoot and Vue.js

This project has been created for the Computer Science major in Mines Saint-Étienne.

## TFSD Todos

Here are the link to the Todos
- [TODO 6](TFSD6.md)  
- [TODO 7](TFSD7.md)  
- [TODO 8](TFSD8.md)  

## Getting Started

### Prerequisites

* Java 17+ and Maven
* Node.js and npm

### Installation of Node.js modules

```
$ npm install
```

### Usage

Run the backend with

```
$ mvn spring-boot:run
```

Then run the frontend with

```
$ npm run serve
```
You should then get the link to the webapp

## MVC Architecture
We are using an MVC architecture to build the webapp
- **Model**: Manages the data and business logic of the application. It receives user input from the controller.
- **View**: Responsible for rendering UI elements. It displays data to the user.
- **Controller**: Acts as an interface between Model and View. It processes user input and makes calls to model objects.

### Code structure
- **Controller**: `CardController.java` in package `flashcard.spring.controller`
- **Model**: `Card.java` and `CardRepository.java` in package `flashcard.spring.model` linked to the H2 database
- **Vue**: All `.vue` files in `vue-3-crud/`

![MVC Architecture](MVC.png)

#### REST API Controller Architecture `port 8080`
The Controller provides REST API to redirect URLs and perform CRUD operations in the database 
| Method | URLs                      | Actions                             |
| ------ | --------------------------| ----------------------------------- |
| POST   | /api/cards                | Create a new Card                   |
| GET    | /api/cards                | Retrieve all Cards                  |
| GET    | /api/cards/:id            | Retrieve a Card by `:id`            |
| PUT    | /api/cards/:id            | Update a Card by `:id`              |
| DELETE | /api/cards/:id            | Delete a Card by `:id`              |
| DELETE | /api/cards                | Delete all Cards                    |
| GET    | /api/cards?word=[word]    | Retrieve all Cards with title containing `word` |


## License
MIT License. See [LICENSE](LICENSE.txt).

## Changelog

- v0.0.1 [First Release](https://github.com/Almiinh/flashcard_webapp/releases/tag/v0.0.1)
