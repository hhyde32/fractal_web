# Fractal Generator Web Application

This project extends my [Java fractal generator](https://github.com/hhyde32/fractal_generator) by adding a web-based interface.

The original project generated fractal images locally using Java. This extended version keeps the original fractal-generation code and adds a Spring Boot web application that allows users to change parameters through a browser.

## 📸 Demo

![Fractal Image](src/main/resources/images/demo_photo.avif)  

## 📦 Installation

Clone the repo:

```bash
git clone https://github.com/hhyde32/fractal_web.git 
```

## 🛠 Usage

Move into the project directory and run the application with Maven:

```bash
cd fractal_web/
mvn spring-boot:run
```

Once the application has started, open the following address in a browser:

```bash
http://localhost:8000
```

## 🧰 Tech Stack
- Java
- Spring Boot
- Spring Web
- Maven
- HTML
- JavaScript
- Java AWT `BufferedImage`
