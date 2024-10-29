<a id="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/jluisalias/price-evaluator">
    <img src="assets/logo.png" alt="Logo">
  </a>

<h3 align="center">The Price Evaluator API</h3>

  <p align="center">
    Prices evaluator API developed using an API First approach, Hexagonal architecture and DDD.
    <br />
    <a href="https://github.com/jluisalias/price-evaluator"><strong>Explore the docs »</strong></a>
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li>
      <a href="#usage">Usage</a>
      <ul>
        <li><a href="#swagger">Swagger</a></li>
        <li><a href="#postman">Postman</a></li>
        <li><a href="#tests">Tests</a></li>
      </ul>
    </li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## About The Project

I set out to create this project to practice some cutting-edge techniques in the world of distributed architectures (DDD, Microservices, API First...), as well as other from software development in general (Clean Code, SOLID, Patterns...).

The definition provided for this project is in the following file:

[Test](./assets/Solution Definition.pdf)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

[![Springboot][Springboot]][Springboot-url]
[![Docker][Docker]][Docker-url]
[![OpenAPI][OpenAPI]][OpenAPI-url]
[![Swagger][Swagger]][Swagger-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

### Requirements

1. The only requirement to install and execute this API is to have Docker and Docker Compose installed in the environment where it's going to be executed.
2. To execute the end-to-end tests it is necessary to download and configure [![Postman][Postman]][Postman-url], it is assumed that the client user knows how to do this. A JSON File to import the tests will be provided.

### Installation

1. Clone the repo, for that, open a terminal, goto the desired location, and then run the following command:
   ```sh
   git clone https://github.com/jluisalias/price-evaluator
   ```

2. Go to /prices, and run:
   ```sh
   .gradlew build
   ```

3. Still in the terminal, go to the root folder, and execute:
   ```sh 
   docker-compose up
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

### Swagger
Once the server is running, go to the browser and type: 
```
http://localhost:8080/swagger-ui/index.html
```
The swagger menu will be loaded to check the API and its features.

### Postman
To execute the end-to-end tests using Postman, first you must import the [Collection JSON File](assets/Price%20Evaluator-collection.json) into your Postman Workspace, additionally you can import the [Environment JSON File](assets/Price%20Evaluator-environment.json), to have some environment variables to manually test the request (Don't forget to switch to the recently imported environment!!!).

Once imported, open the Collection and click on the "Run" icon: <br />
<img src="assets/run_collection.png" alt="Run Collection">

In Data section, click on "Select File button" and browse and choose one of the two files provided in the assets folder:
- [Correct Values File](assets/postman_test_correct_inputs.json): are the values provided in the solution definition, the tests should pass.
- [Wrong Values File](assets/postman_test_wrong_inputs.json): are purposely wrong values that will make the tests fail.

Correct Results:<br />
<img src="assets/e2e_test_execution.png" alt="End-to-end Test Results">

### Tests
To execute the suite of unit and integration tests in the /prices directory, use the following command:
   ```sh
   .gradlew test --info
   ```
After execution, you should see an output similar to the following:<br />
<img src="assets/test_execution.png" alt="Test execution" width="1000" height="70">

To view the test report, open your browser and navigate to the generated report path. Copy the second path listed in the output, append /index.html, and load this URL in your browser to access a detailed report of the test execution.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

José Luis Alías - [@joseLalias](https://twitter.com/joseLalias) - jluis.alias@gmail.com

Project Link: [https://github.com/jluisalias/price-evaluator](https://github.com/jluisalias/price-evaluator)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/jluisalias
[logo]: assets/logo.png
[Docker]: https://img.shields.io/badge/docker-0db7ed?style=for-the-badge&logo=docker&logoColor=white
[Docker-url]: https://www.docker.com/
[Springboot]: https://img.shields.io/badge/springboot-6db33f?style=for-the-badge&logo=springboot&logoColor=white
[Springboot-url]: https://spring.io/projects/spring-boot
[OpenAPI]: https://img.shields.io/badge/open%20api-6BA539?style=for-the-badge&logo=openapiinitiative&logoColor=white
[OpenAPI-url]: https://www.openapis.org/
[Swagger]: https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=333333
[Swagger-url]: https://swagger.io/
[Postman]: https://img.shields.io/badge/postman-FF6C37?style=plastic&logo=postman&logoColor=white
[Postman-url]: https://www.postman.com/