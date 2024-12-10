# Review Challenge

This challenge is about reviewing code. We’ve built a solution to our normal [backend challenge](https://github.com/coveo/backend-coding-challenge), and we want you to review it. To make things clear, you don't have to prepare any code nor follow the README on GitHub. The GitHub link is only given to explain what the project in this archive accomplishes. The code to review is in this archive (see instructions below to run it). To add some complexity, we included both a UI on top of the backend project and an additional C++ version of the backend.

All steps listed below will be required during the interview. We expect you to do them BEFORE we meet.

- Make sure you can run the code (backend in **Java or C++**, and frontend) and understand what's going on.
- Review the code and take notes on improvements or changes. Assume this code is in production right now, and plan changes for the next few releases. With an agile process in mind, note what to address in the first release, second release, etc. This will help us focus our discussion on priorities.
- Set up an environment where you can hit breakpoints and debug relevant parts of the code (backend, frontend, or both for full-stack). Use any application you prefer, but ensure you're comfortable debugging before the interview.
- Have an editor or IDE ready to code during the interview.
- Have Git installed.
- Make sure you understand the project thoroughly.

We didn’t follow our normal standards, so you should have some observations about the code.

Regardless of whether you’re a backend, frontend, or full-stack developer, we expect you to understand the project at a high level and form an opinion on it. If you’re applying for a backend role, we don’t expect as much frontend expertise, and vice versa. We’ll adjust focus based on the targeted position.

## Running the Backend

You have two options to run the backend, depending on your expertise and preferences:

1. **Java Backend**:
    - It’s built with Java 17, Maven, and Spring Boot. You will need Java 17 and Maven installed. You can run it with:

      - **IDE (Backend/Full-stack candidates)**: Open the main class (`./backend/src/main/java/com/coveo/challenge/ReviewChallengeApplication.java`) in an IDE and start it (debug or run mode).

      - **Command Line (Frontend candidates)**: Run `mvn clean package -T1C -U -Dmaven.test.skip=true` in the `./backend` folder, then execute `java -jar target/review-challenge-0.0.1-SNAPSHOT.jar`. If port 8080 is unavailable, use `-Dserver.port=8081`, but note the frontend expects port 8080.

2. **C++ Backend**:
    - This is the same challenge as the Java backend, implemented in C++. If you choose to work with the C++ backend, follow these steps:

      - Ensure you have CMake and Ninja installed.
      - Navigate to the `backend-cpp` directory.
      - Run the following commands to build the project:

        ```bash
        cmake -G Ninja -B build
        cmake --build build
        ctest --test-dir build
        ```

      - After building, locate the executable within the `build` directory (typically named `BackendCpp` or similar).
      - To run the application, use:

        ```bash
        cd build
        ./BackendCpp
        ```

      - You must run the program from the `build` directory, or else the file `cities_canada-usa.tsv` will not be found. The program expects this file to be in the current directory.
      - The C++ backend should run on port 8080 by default. If you need to use a different port, you can modify the port in the source code or configuration if supported.

## Running the Frontend

The frontend is built using TypeScript and React. You will need Node.js installed. To run it:

1. Navigate to the `frontend` folder.
2. Run `npm install`.
3. Start the application with `npm run start`.

This will open a browser tab where you can search cities. Ensure the backend is running first; otherwise, the search feature will not work.
