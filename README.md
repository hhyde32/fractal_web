# Fractal Generator Web Application

This project extends my original Java fractal generator by adding a web-based interface.

The original project generated fractal images locally using Java. This extended version keeps the original fractal-generation code and adds a Spring Boot web application that allows users to change parameters through a browser.

## Features

- Generates fractals using the original Java implementation
- Provides a web interface for changing fractal parameters
- Allows users to change:
  - Image size
  - Fractal width
  - Real origin
  - Imaginary origin
  - Colouring mode
- Returns generated fractals as PNG images
- Preserves the original command-line image-generation functionality

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Maven
- HTML
- JavaScript
- Java AWT `BufferedImage`

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/example/fractal/
    │       ├── FractalWebApplication.java
    │       ├── FractalController.java
    │       ├── Main.java
    │       ├── Complex.java
    │       ├── Polynomial.java
    │       ├── Secant.java
    │       └── Other fractal classes
    │
    └── resources/
        └── static/
            └── index.html
'''
