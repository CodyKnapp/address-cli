# Empora Assessment

## Requirements
Create a config.properties file in `src/main/resources` with the following contents:
```
smarty.auth.id=your_auth_id
smarty.auth.token=your_auth_token
smarty.auth.license=your_auth_license
```
NOTE: For the integration test to work, a config.properties file will need to be created in `src/it/resources` with the same contents.
I wanted to allow for flexibility between integration test and production accounts.

## Build & Run
This project uses gradle as the build tool with the Spring Boot plugin. 
To run the project through gradle, run the following command from the root directory of the project:
```
./gradlew bootRun --args=inputFile.csv
```

To build the project, run the following command:
```
./gradlew build
```
That will create a jar in the build/libs directory. To run the jar, run the following command from the root directory of the project:
```
java -jar build/libs/empora-assessment-0.0.1-SNAPSHOT.jar sample-file.csv
```

The csv can be swapped for any other csv file that follows the same format as the sample-file.csv. The sample-file.csv is included for
convenience.

## Testing
This project contains both unit and integration tests that are run using separate commands.
The integration tests hit the live Smarty API and will require a valid Smarty account to run.
The details for the Smarty account need to be included in a config.properties file as noted above in order for the integration tests to run successfully.
The gradle check task will run both the unit and integration tests. Each test suite can be run independently as well.

To run only the unit tests, use the gradle `test` task:
```
./gradlew test
```

To run only the integration tests, use the gradle `integrationTest` task:
```
./gradlew integrationTest
```

To run both the unit and integration tests, use the gradle `check` task (this runs automatically as part of build):
```
./gradlew check
```

The tests are split the way they are because making the AddressVerificationClient accept a RestClient as a dependency felt like overkill.
The RestClient is a fairly complicated system to mock so in the interest of time, I decided to only use integration tests for the client class.

## Miscellaneous
I decided to use Spring Boot for this project because as I started working through it, I realized I was basically going to 
end up building several things Spring Boot already does. I wanted a convenient REST client and dependency injection and
Spring provides both of those things. Spring Boot simply made it easy to inject dependencies as well as configure the runners.

I had not previously used several things I used in this project. 
I am fairly new to gradle, having always used maven in the past. 
I found that to be a bit of a struggle due to my desire to split the tests.
I had also never leveraged profiles or the CommandLineRunner interface in Spring Boot. I found both to be very useful.
It was an opportunity to branch out from familiar territory a bit and do some learning.

I wanted to leave the main Spring Boot entry point as isolated as possible, so `MainApplication` is very simple and the
functionality is injected through other means. Spring has a `CommandLineRunner` interface that injects the command line
arguments into a simple run function. This seemed like an appropriate way to process a file based on the args to the app.
I knew separating things this way would allow for the most testable project and that was a priority for me.

I considered making the `AddressVerificationClient` have multiple implementations - one that included batching of addresses into a POST request
and one that does each request independently through the GET endpoint. I ultimately decided not to spend the time on adding that.
In a production system I would very likely add that in order to be sure that the system could handle a large number of addresses.
I would also very likely take more steps to make the address checking asynchronous in order to pipeline requests and speed up the process.

