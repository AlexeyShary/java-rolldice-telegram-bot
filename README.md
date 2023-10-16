# Java Roll Dice Telegram Bot

This is a Telegram bot that simulates dice rolls for users. Processed requests are saved in a database, and you can view statistics by visiting the provided HTTP endpoint.

## Deployed example

- https://t.me/JavaRollDiceBot - Telegram bot
- http://185.117.153.156:8081/stats - Statistics

## Bot usage

The bot expects a message from the user in the format [x]d[y], where x is the number of rolls, and y is the number of sides on the die. In response to the user's message, the bot will provide the results of rolling the dice. For example, if the user sends the message "2d6," the bot will return two numbers representing the outcomes of rolling two six-sided dice.

## Stack

This project was built using the following technologies and libraries:

- [Java 17](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Spring Boot](https://spring.io/projects/spring-boot) - a framework for Java application development
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - a library for data management
- [Thymeleaf](https://www.thymeleaf.org/) - a templating engine for creating web pages
- [H2 Database](https://www.h2database.com/html/main.html) - an embedded database for development
- [Telegram Bots API](https://core.telegram.org/bots/api) - for creating Telegram bots
- [Lombok](https://projectlombok.org/) - a library for simplifying Java development
- [MapStruct](https://mapstruct.org/) - a library for generating mappings between Java objects

Dependency and build management:

- [Maven](https://maven.apache.org/)

The project is built using the [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html).

Compilation and annotation processing are handled by the [Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/).

## How to deploy

For deployment in the project, you need to add an application-secret.properties file in which you should specify the parameters telegram.bot.name and telegram.bot.token. You can obtain these parameters from https://t.me/BotFather.

Everything else is ready for building using Maven's package command.

<p align="center">
  <img src="java_roll_dice_bot.png" alt="Bot Image">
</p>