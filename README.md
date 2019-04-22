# Mars Rover API

Simple component that allows to manage the Mars Rover vehicle.

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:
```
git@github.com:ajdafonte/mars-rover.git
```

### Build component

Remark: This also execute all tests.

Execute the following gradle task:
```
gradlew.bat build
```

### Running the tests (only)

Execute the following gradle task:
```
gradlew.bat test
```

### Running the component

Execute the following gradle task:
```
gradlew.bat bootRun
```

### Import into IDE

This is a Gradle project (build.gradle.kts). Here are some instructions on how to import a Gradle project in some of the most popular IDEs:
- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import
- Eclipse - https://www.vogella.com/tutorials/EclipseGradle/article.html#import-an-existing-gradle-project

## Technical Comments

In this section will be described some technical details of this project.

### Stack
  
Here's a short summary of the tech stack used in the development of this component.
- Java 8, Spring Boot, Gradle
- Main dependencies: 
    - spring-boot-starter-web, lombok, swagger, junit5, mockito, hamcrest  

### Component Configuration

In file `application.yml` some properties were defined that allows to:
 - Define component listening port - `server.port`
 - Define logging level - `logging.level`
 - Define multimedia configuration - `marsrovers.multimediaConfig`  

### Component Functionality

#### Available endpoints

For more details about these endpoints (input and output data) open http://localhost:<port>/swagger-ui.html. 
Here's a short summary:

#### Movements

- `GET /movements` - Retrieve all the movements made by the Mars Rover.
- `GET /movements/{id}` - Retrieve a movement made by the Mars Rover specified by the ID.
- `POST /movements` - Create a new movement of the Mars Rover.
    - Input data: coordinates (latitude and longitude)
    - Constraints: 
        - Latitude should be in range [-90;90]
        - Longitude should be in range [0;360]

With these endpoints is possible to manage the movements of the Mars Rover.

#### Messages

- `GET /messages` - Retrieve all the messages sent by the Mars Rover.
- `GET /messages/{id}` - Retrieve a message sent by the Mars Rover specified by the ID.
- `POST /messages` - Create a new message so that Mars Rover can transmit it.
    - Input data: text of the message and the dialect by which the message should be transmitted. 
    - Constraints: Available dialects (UK, USA, JAMAICA; AUSTRALIA)

With this endpoints is possible access/transmit messages of/for the Mars Rover.


### Additional Remarks

- A dummy in-memory storage was used during the development of this API (no database of any kind was used, like MySQL, H2, etc). 