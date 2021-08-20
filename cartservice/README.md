<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]




<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation"></a>

  <h3 align="center">Jean Station - CartService - ReadMe</h3>

  <p align="center">
    CartService is a microservice for the Jean Station online store.
    <br />
    <a href="https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation/-/wikis/home"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation">View Demo</a>
    ·
    <a href="https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation/-/issues/new">Report Bug</a>
  </p>


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Microservice</a>
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
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE MICROSERVICE -->
## About The Microservice

[![Product Name Screen Shot][project-logo]](https://example.com)

CartService is a microservice for the Jean Station online store. 

Here's the main purpose of this microservice:
* Provide RESTful endpoints for CRUD operation related to user cart management.
* Provide RESTful endpoints for front-end interface


### Built With

* [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview)
* [MongoDB Atlas](https://www.mongodb.com/cloud/atlas)
* [Project Lombok](https://projectlombok.org/)



<!-- GETTING STARTED -->
## Getting Started

To run the microservice, load the project in an IDE such as Intellij or Visual Studio Code.
Then, open the /productservice folder. Run the ProductServiceApplication.java located at
/productservice/src/main/java/com/stackroute/

### Prerequisites

This is a maven project. Your IDE will need to have Maven enabled.

### Installation

1. Clone the repo
   ```sh
   git clone https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation.git
   ```
2. Import the project into your IDE of choice
3. Let Maven download the dependencies
4. Run CartServiceApplication.java



<!-- USAGE EXAMPLES -->
## Usage

* POST - Create a cart
  * localhost:8090/api/v1/cart
* GET - Retrieve a cart by ID
   * localhost:8090/api/v1/cart/{cartId}
* GET - Retrieve all carts
  * localhost:8090/api/v1/carts
* PUT - Update a cart by ID
  * localhost:8090/api/v1/cart
* DELETE - Delete a cart by ID
  * localhost:8090/api/v1/cart/{cartId}



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Jesse Tsang - [LinkedIn](https://www.linkedin.com/in/jesse-tsang/) 

Jia Mo - [LinkedIn](https://www.linkedin.com/in/Jia-Mo/)

Kennan Hoa - [LinkedIn](https://www.linkedin.com/in/Kennan-Hoa/)

Project Link: [Gitlab - Jean Station](https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [GitHub Best README Template](https://github.com/othneildrew/Best-README-Template)






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation/-/graphs/feature-product-test
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://gitlab-cgi.stackroute.in/cgi-canada-wave1-capstone-projects/jeanstation/-/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png
[project-logo]: images/jeanStationLogo.png
