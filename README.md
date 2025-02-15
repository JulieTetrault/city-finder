# City Finder

This project is a review challenge of a solution built for Coveo's [backend challenge](https://github.com/coveo/backend-coding-challenge) that needs improvement.
Each commit in the master branch represents a different release in production, which are listed below.

## Release 1: Improve Project Configuration

- Improve README files.
- Improve .gitignore files.
- Clean up project dependencies
  - **Pin** dependencies version for both the backend and frontend.
  - Set up a dependency updater tool, like **Renovate**, to take care of updating the versions instead of doing it manually.
- Set up runtime development tools, ensuring consistency across different environments
  - Install maven wrapper and add `.sdkmanrc` file in backend
  - Install yarn wrapper and add `.nvmrc` file in frontend
- Add missing configuration regarding:
  - Style check
  - Code coverage
  - CI/CD
  - Documentation generation
    - Swagger for backend

## Release 2: Improve Backend Architecture
- Set up a **layered architecture** without changing the contract between the frontend and backend, in order to respect the **DIP** and **SRP** principles.
  - Add the following layers:
      - Presentation layer
      - Persistence layer
      - Domain layer
      - Service layer
  - Create DTOs
- Improve the performance at the persistence level:
  - Set up an in-memory database with Hibernate.
  - Read the `.tsv` file only once, when the app is launched, and populate the database.
  - Move the search logic at the repository level
    - Use the query and pagination features provided by JPARepository
- Add missing unit tests
  - Use a BDD structure and add dependencies like Mockito and AssertJ

## Release 3: Improve Backend Logging and Exception Handling
- Add a proper logger, like Slf4j
  - Integrate the logger with a third-party monitoring tool
- Add a proper exception handler
  - For internal service error, log the error's details and return a generic message error.
- Handle CORS correctly
  - Set up allowed origins
- Add integration tests with cucumber

## Release 4: Improve Backend-Frontend Contract
- Before changing the actual contract, make some changes regarding the **configuration**
  - Make those fields configurable:
    - default longitude
    - default latitude
    - longitude range
    - latitude range 
  - Adapt the JPARepository queries so they can be more dynamic and take into account the configuration
    - Use the **Spring Data JPA Specifications API**, which is an abstraction of the **Criteria API**.
- Make changes to the contract, since the term "suggestions" does not make any sense in a REST Api
  - Deprecate the old suggestions controller:
    - Add a @Deprecated annotation
    - Log a warning whenever the deprecated endpoints are accessed
    - Communicate with the frontend teams about the deprecation timeline
  - Create a new controller (CityResource) with versioning:
    - Introduce a new base path `/v2/cities`
      - The search endpoint can be suffix with `/search`
    - Rename q to name
    - Modify the response structure as needed
  - Frontend implementation strategy:
    - The frontend should continue using /suggestions for now
    - Provide a migration period where both endpoints are available
    - Update the frontend to switch to /v2/cities and adjust request/response handling accordingly
    - After the migration period, once the backend team confirms that there is no more logged warnings regarding deprecated endpoints accessed, remove the SuggestionsResource.